package com.store.application.domain;

import java.math.BigDecimal;
import java.util.List;

public class OrderReceipt {
    private BigDecimal finalPrice;
    private BigDecimal priceWithDiscount;
    private BigDecimal totalSaved;
    private List<Product> orderedProducts;

    public OrderReceipt() {
    }

    public OrderReceipt(BigDecimal finalPrice, BigDecimal priceWithDiscount, BigDecimal totalSaved, List<Product> orderedProducts) {
        this.finalPrice = finalPrice;
        this.priceWithDiscount = priceWithDiscount;
        this.totalSaved = totalSaved;
        this.orderedProducts = orderedProducts;
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

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof OrderReceipt)) return false;
        final OrderReceipt other = (OrderReceipt) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$finalPrice = this.getFinalPrice();
        final Object other$finalPrice = other.getFinalPrice();
        if (this$finalPrice == null ? other$finalPrice != null : !this$finalPrice.equals(other$finalPrice))
            return false;
        final Object this$priceWithDiscount = this.getPriceWithDiscount();
        final Object other$priceWithDiscount = other.getPriceWithDiscount();
        if (this$priceWithDiscount == null ? other$priceWithDiscount != null : !this$priceWithDiscount.equals(other$priceWithDiscount))
            return false;
        final Object this$totalSaved = this.getTotalSaved();
        final Object other$totalSaved = other.getTotalSaved();
        if (this$totalSaved == null ? other$totalSaved != null : !this$totalSaved.equals(other$totalSaved))
            return false;
        final Object this$orderedProducts = this.getOrderedProducts();
        final Object other$orderedProducts = other.getOrderedProducts();
        if (this$orderedProducts == null ? other$orderedProducts != null : !this$orderedProducts.equals(other$orderedProducts))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof OrderReceipt;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $finalPrice = this.getFinalPrice();
        result = result * PRIME + ($finalPrice == null ? 43 : $finalPrice.hashCode());
        final Object $priceWithDiscount = this.getPriceWithDiscount();
        result = result * PRIME + ($priceWithDiscount == null ? 43 : $priceWithDiscount.hashCode());
        final Object $totalSaved = this.getTotalSaved();
        result = result * PRIME + ($totalSaved == null ? 43 : $totalSaved.hashCode());
        final Object $orderedProducts = this.getOrderedProducts();
        result = result * PRIME + ($orderedProducts == null ? 43 : $orderedProducts.hashCode());
        return result;
    }
}
