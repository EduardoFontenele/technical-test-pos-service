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

public class ProcessOrderUseCase implements ProcessOrderInputPort {

    private final FindProductFullInformationOutputPort findProductFullInformationOutputPort;

    public ProcessOrderUseCase(FindProductFullInformationOutputPort findProductFullInformationOutputPort) {
        this.findProductFullInformationOutputPort = findProductFullInformationOutputPort;
    }

    @Override
    public OrderReceipt process(List<Product> orderedProducts) {
        BigDecimal totalPriceWithoutDiscount = BigDecimal.ZERO;
        BigDecimal totalPriceWithDiscount = BigDecimal.ZERO;
        BigDecimal totalSaved = BigDecimal.ZERO;
        List<Product> processedProducts = new ArrayList<>();

        for (Product orderedProduct : orderedProducts) {
            Product product = findProductFullInformationOutputPort.findProduct(orderedProduct.getId());
            product.setOrderedQuantity(orderedProduct.getOrderedQuantity());

            Promotion promotion = product.getPromotions().stream().findFirst().orElse(null);

            BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(product.getOrderedQuantity()));
            totalPriceWithoutDiscount = totalPriceWithoutDiscount.add(productTotalPrice).setScale(2, RoundingMode.HALF_UP);

            if (promotion != null) {
                BigDecimal discountedPrice = calculateDiscountedPrice(product, promotion);
                totalPriceWithDiscount = totalPriceWithDiscount.add(discountedPrice).setScale(2, RoundingMode.HALF_UP);
                totalSaved = totalSaved.add(productTotalPrice.subtract(discountedPrice)).setScale(2, RoundingMode.HALF_UP);
            } else {
                totalPriceWithDiscount = totalPriceWithDiscount.add(productTotalPrice).setScale(2, RoundingMode.HALF_UP);
            }

            processedProducts.add(product);
        }
        return new OrderReceipt(totalPriceWithoutDiscount, totalPriceWithDiscount, totalSaved, processedProducts);
    }

    private BigDecimal calculateDiscountedPrice(Product product, Promotion promotion) {
        BigDecimal productTotalPrice = product.getPrice().multiply(BigDecimal.valueOf(product.getOrderedQuantity()));
        switch (promotion.getType()) {
            case "FLAT_PERCENT":
                return productTotalPrice.multiply(BigDecimal.valueOf(1 - promotion.getAmount() / 100.0)).setScale(2, RoundingMode.HALF_UP);
            case "BUY_X_GET_Y_FREE":
                if (product.getOrderedQuantity() >= promotion.getRequiredQty()) {
                    return productTotalPrice.subtract(product.getPrice()).setScale(2, RoundingMode.HALF_UP);
                }
                return productTotalPrice.setScale(2, RoundingMode.HALF_UP);
            case "QTY_BASED_PRICE_OVERRIDE":
                if (product.getOrderedQuantity() >= promotion.getRequiredQty()) {
                    return productTotalPrice.subtract(product.getPrice().multiply(BigDecimal.valueOf(promotion.getRequiredQty()))).add(promotion.getPrice()).setScale(2, RoundingMode.HALF_UP);
                }
                return productTotalPrice.setScale(2, RoundingMode.HALF_UP);
            default:
                return productTotalPrice.setScale(2, RoundingMode.HALF_UP);
        }
    }
}