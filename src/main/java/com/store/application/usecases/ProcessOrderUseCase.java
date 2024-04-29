package com.store.application.usecases;

import com.store.application.domain.OrderReceipt;
import com.store.application.domain.Product;
import com.store.application.domain.Promotion;
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
        BigDecimal finalPriceWithDiscount = new BigDecimal(0);
        BigDecimal finalPrice = new BigDecimal(0);
        BigDecimal savedMoney = new BigDecimal(0);
        List<Product> foundProducts = new ArrayList<>();

        for (Product orderedProduct : orderedProducts) {
            Product foundProduct = findProductFullInformationOutputPort.findProduct(orderedProduct.getId());

            foundProduct.setOrderedQuantity(orderedProduct.getOrderedQuantity());

            Optional<Promotion> promotion = foundProduct.getPromotions().stream().findFirst();

            if (promotion.isEmpty()) {
                finalPrice = finalPrice.add(foundProduct.getPrice().multiply(new BigDecimal(orderedProduct.getOrderedQuantity())));
            }

            if (promotion.isPresent()) {
                Promotion foundPromo = promotion.get();

                if (foundPromo.getType().equals("FLAT_PERCENT")) {
                    double discountAppliedInPercent = (double) (100 - foundPromo.getAmount()) / 100;
                    BigDecimal totalValue = foundProduct.getPrice().multiply(new BigDecimal(foundProduct.getOrderedQuantity()));
                    BigDecimal priceWithDiscount = totalValue.multiply(new BigDecimal(discountAppliedInPercent));

                    finalPrice = finalPrice.add(totalValue);
                    finalPriceWithDiscount = finalPriceWithDiscount.add(priceWithDiscount);
                    savedMoney = savedMoney.add(totalValue.subtract(priceWithDiscount)).setScale(2, RoundingMode.UP);
                }

                if (foundPromo.getType().equals("BUY_X_GET_Y_FREE")) {
                    BigDecimal totalValue = foundProduct.getPrice().multiply(new BigDecimal(orderedProduct.getOrderedQuantity()));

                    if (foundProduct.getOrderedQuantity() >= foundPromo.getRequiredQty()) {
                        BigDecimal priceWithDiscount = totalValue.subtract(foundProduct.getPrice());

                        finalPrice = finalPrice.add(totalValue);
                        savedMoney = savedMoney.add(foundProduct.getPrice());
                        finalPriceWithDiscount = finalPriceWithDiscount.add(priceWithDiscount);
                    } else {
                        finalPrice = finalPrice.add(totalValue);
                    }
                }

                // This one here is a little bit confusing for me. But I'll assume that every promotion is unique,
                // since it has an ID


            }

            foundProducts.add(foundProduct);
        }

        OrderReceipt orderReceipt = new OrderReceipt();
        orderReceipt.setFinalPrice(finalPrice);
        orderReceipt.setTotalSaved(savedMoney);
        orderReceipt.setOrderedProducts(foundProducts);
        orderReceipt.setPriceWithDiscount(finalPriceWithDiscount);

        return orderReceipt;
    }


}
