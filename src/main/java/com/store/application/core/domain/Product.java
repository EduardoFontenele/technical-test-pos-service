package com.store.application.core.domain;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private String id;
    private String name;
    private BigDecimal price;
    private int orderedQuantity;
    private List<Promotion> promotions;
    private String promoApplied;

    public Product() {
    }

    public Product(String id, String name, BigDecimal price, int orderedQuantity, List<Promotion> promotions, String promoApplied) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.orderedQuantity = orderedQuantity;
        this.promotions = promotions;
        this.promoApplied = promoApplied;
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Promotion> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<Promotion> promotions) {
        this.promotions = promotions;
    }

    public Integer getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(Integer orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    public String getPromoApplied() {
        return promoApplied;
    }

    public void setPromoApplied(String promoApplied) {
        this.promoApplied = promoApplied;
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Product)) return false;
        final Product other = (Product) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        if (this.getOrderedQuantity() != other.getOrderedQuantity()) return false;
        final Object this$promotions = this.getPromotions();
        final Object other$promotions = other.getPromotions();
        if (this$promotions == null ? other$promotions != null : !this$promotions.equals(other$promotions))
            return false;
        final Object this$promoApplied = this.getPromoApplied();
        final Object other$promoApplied = other.getPromoApplied();
        if (this$promoApplied == null ? other$promoApplied != null : !this$promoApplied.equals(other$promoApplied))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Product;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        result = result * PRIME + this.getOrderedQuantity();
        final Object $promotions = this.getPromotions();
        result = result * PRIME + ($promotions == null ? 43 : $promotions.hashCode());
        final Object $promoApplied = this.getPromoApplied();
        result = result * PRIME + ($promoApplied == null ? 43 : $promoApplied.hashCode());
        return result;
    }

    public static class ProductBuilder {
        private String id;
        private String name;
        private BigDecimal price;
        private int orderedQuantity;
        private List<Promotion> promotions;
        private String promoApplied;

        ProductBuilder() {
        }

        public ProductBuilder id(String id) {
            this.id = id;
            return this;
        }

        public ProductBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ProductBuilder price(BigDecimal price) {
            this.price = price;
            return this;
        }

        public ProductBuilder orderedQuantity(int orderedQuantity) {
            this.orderedQuantity = orderedQuantity;
            return this;
        }

        public ProductBuilder promotions(List<Promotion> promotions) {
            this.promotions = promotions;
            return this;
        }

        public ProductBuilder promoApplied(String promoApplied) {
            this.promoApplied = promoApplied;
            return this;
        }

        public Product build() {
            return new Product(this.id, this.name, this.price, this.orderedQuantity, this.promotions, this.promoApplied);
        }

        public String toString() {
            return "Product.ProductBuilder(id=" + this.id + ", name=" + this.name + ", price=" + this.price + ", orderedQuantity=" + this.orderedQuantity + ", promotions=" + this.promotions + ", promoApplied=" + this.promoApplied + ")";
        }
    }
}
