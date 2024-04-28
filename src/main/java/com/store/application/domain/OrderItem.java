package com.store.application.domain;

public class OrderItem {
    private String productId;
    private Integer quantity;

    public OrderItem(String productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItem() {
    }

    public static OrderItemBuilder builder() {
        return new OrderItemBuilder();
    }

    public String getProductId() {
        return this.productId;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static class OrderItemBuilder {
        private String productId;
        private Integer quantity;

        OrderItemBuilder() {
        }

        public OrderItemBuilder productId(String productId) {
            this.productId = productId;
            return this;
        }

        public OrderItemBuilder quantity(Integer quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this.productId, this.quantity);
        }

        public String toString() {
            return "OrderItem.OrderItemBuilder(productId=" + this.productId + ", quantity=" + this.quantity + ")";
        }
    }
}
