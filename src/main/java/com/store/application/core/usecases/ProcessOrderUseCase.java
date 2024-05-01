package com.store.application.core.usecases;

import com.store.application.core.domain.OrderReceipt;
import com.store.application.core.domain.Product;
import com.store.application.core.domain.Promotion;
import com.store.application.core.domain.PromotionType;
import com.store.application.ports.input.FindProductFullInformationOutputPort;
import com.store.application.ports.input.ProcessOrderInputPort;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProcessOrderUseCase implements ProcessOrderInputPort {

    private final FindProductFullInformationOutputPort findProductFullInformationOutputPort;

    public ProcessOrderUseCase(FindProductFullInformationOutputPort findProductFullInformationOutputPort) {
        this.findProductFullInformationOutputPort = findProductFullInformationOutputPort;
    }

    @Override
    public OrderReceipt process(List<Product> orderedProducts) {
        BigDecimal totalPriceOfOrderWithoutDiscount = new BigDecimal(0);
        BigDecimal totalPriceOfOrderWithDiscount = new BigDecimal(0);
        BigDecimal savedMoney = new BigDecimal(0);
        List<Product> foundProducts = new ArrayList<>();

        for (Product orderedProduct : orderedProducts) {
            Product foundProduct = findProductFullInformationOutputPort.findProduct(orderedProduct.getId());
            foundProduct.setOrderedQuantity(orderedProduct.getOrderedQuantity());

            Optional<Promotion> promotion = foundProduct.getPromotions().stream().findFirst();

            if (promotion.isEmpty()) {
                totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithoutDiscount.add(foundProduct.getPrice().multiply(new BigDecimal(orderedProduct.getOrderedQuantity())));
                foundProduct.setPromoApplied("N/A");
            }

            if (promotion.isPresent()) {
                Promotion foundPromotion = promotion.get();

                if (foundPromotion.getType().equals("FLAT_PERCENT")) {
                    double discountAppliedInPercent = (double) (100 - foundPromotion.getAmount()) / 100;
                    BigDecimal priceOfProductMultipliedByQuantity = foundProduct.getPrice().multiply(new BigDecimal(foundProduct.getOrderedQuantity()));
                    BigDecimal priceOfProductsWithDiscount = priceOfProductMultipliedByQuantity.multiply(new BigDecimal(discountAppliedInPercent));

                    totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithoutDiscount.add(priceOfProductMultipliedByQuantity);
                    totalPriceOfOrderWithDiscount = totalPriceOfOrderWithDiscount.add(priceOfProductsWithDiscount);
                    savedMoney = savedMoney.add(priceOfProductMultipliedByQuantity.subtract(priceOfProductsWithDiscount)).setScale(2, RoundingMode.UP);
                    foundProduct.setPromoApplied(PromotionType.FLAT_PERCENT.getDescription().replace("[percent]", foundPromotion.getAmount().toString()));
                }

                if (foundPromotion.getType().equals("BUY_X_GET_Y_FREE")) {
                    BigDecimal priceOfProductMultipliedByQuantity = foundProduct.getPrice().multiply(new BigDecimal(foundProduct.getOrderedQuantity()));

                    if (foundProduct.getOrderedQuantity() >= foundPromotion.getRequiredQty()) {
                        BigDecimal priceOfProductsWithDiscount = priceOfProductMultipliedByQuantity.subtract(foundProduct.getPrice());

                        totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithoutDiscount.add(priceOfProductMultipliedByQuantity);
                        savedMoney = savedMoney.add(foundProduct.getPrice());
                        totalPriceOfOrderWithDiscount = totalPriceOfOrderWithDiscount.add(priceOfProductsWithDiscount);
                    } else {
                        totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithoutDiscount.add(priceOfProductMultipliedByQuantity);
                        totalPriceOfOrderWithDiscount = totalPriceOfOrderWithDiscount.add(priceOfProductMultipliedByQuantity);
                    }

                    foundProduct.setPromoApplied(
                            PromotionType.BUY_X_GET_Y_FREE.getDescription().replace("[required_quantity]", foundPromotion.getRequiredQty().toString()));
                }


                if (foundPromotion.getType().equals("QTY_BASED_PRICE_OVERRIDE")) {
                    if (foundProduct.getOrderedQuantity() >= foundPromotion.getRequiredQty()) {
                        BigDecimal priceOfProductMultipliedByQuantityWithPromo = foundProduct.getPrice()
                                .multiply(new BigDecimal(foundProduct.getOrderedQuantity() - foundPromotion.getRequiredQty()));

                        BigDecimal priceOfProductMultipliedByQuantityWithoutPromo = foundProduct.getPrice()
                                .multiply(new BigDecimal(foundProduct.getOrderedQuantity()));

                        savedMoney = savedMoney.add(
                                foundProduct.getPrice().multiply(new BigDecimal(foundPromotion.getRequiredQty()))
                                        .subtract(foundPromotion.getPrice()));

                        totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithDiscount.add(priceOfProductMultipliedByQuantityWithoutPromo);
                        totalPriceOfOrderWithDiscount = totalPriceOfOrderWithDiscount.add(priceOfProductMultipliedByQuantityWithPromo.add(foundPromotion.getPrice()));
                    } else {
                        totalPriceOfOrderWithoutDiscount = totalPriceOfOrderWithDiscount.add(foundProduct.getPrice());
                        totalPriceOfOrderWithDiscount = totalPriceOfOrderWithDiscount.add(foundProduct.getPrice());
                    }

                    foundProduct.setPromoApplied(
                            PromotionType.QTY_BASED_PRICE_OVERRIDE.getDescription().replace("[required_quantity]", foundPromotion.getRequiredQty().toString())
                                    .replace("[price]", foundPromotion.getPrice().toString())
                    );
                }

            }

            foundProducts.add(foundProduct);
        }

        return new OrderReceipt(totalPriceOfOrderWithoutDiscount, totalPriceOfOrderWithDiscount, savedMoney, foundProducts);
    }
}
