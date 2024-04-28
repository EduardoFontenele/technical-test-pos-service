package com.store.application.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderReceipt {
    private BigDecimal finalPrice;
    private BigDecimal totalSaved;
    private List<Product> orderedProducts;

    public OrderReceipt() {
    }

    public BigDecimal getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public BigDecimal getTotalSaved() {
        return totalSaved;
    }

    public void setTotalSaved(BigDecimal totalSaved) {
        this.totalSaved = totalSaved;
    }

    public List<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
