package com.store.application.usecases;

import com.store.application.domain.OrderItem;
import com.store.application.domain.OrderReceipt;
import com.store.application.domain.Product;
import com.store.application.domain.Promotion;
import com.store.application.ports.input.FindProductFullInformationOutputPort;
import com.store.application.ports.input.ProcessOrderInputPort;

import java.math.BigDecimal;
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
        BigDecimal finalPrice = new BigDecimal(0);
        BigDecimal savedMoney = new BigDecimal(0);
        List<Product> foundProducts = new ArrayList<>();

        for (Product orderedProduct : orderedProducts) {
            Product foundProduct = findProductFullInformationOutputPort.findProduct(orderedProduct.getId());

            foundProduct.setOrderedQuantity(orderedProduct.getOrderedQuantity());

            Optional<Promotion> promotion = foundProduct.getPromotions().stream().findFirst();
            Integer quantity = foundProduct.getOrderedQuantity();
            BigDecimal productPrice = foundProduct.getPrice();

            if (promotion.isEmpty()) {
                finalPrice = finalPrice.add(productPrice.multiply(new BigDecimal(quantity)));
            }

            foundProducts.add(foundProduct);
        }

        OrderReceipt orderReceipt = new OrderReceipt();
        orderReceipt.setFinalPrice(finalPrice);
        orderReceipt.setTotalSaved(savedMoney);
        orderReceipt.setOrderedProducts(foundProducts);
        return orderReceipt;
    }
}
