package com.store.application.core.domain;

import java.math.BigDecimal;

public class Promotion {
    private String id;
    private String type;
    private Integer requiredQty;
    private Integer freeQty;
    private Integer amount;
    private BigDecimal price;

    public Promotion() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getRequiredQty() {
        return requiredQty;
    }

    public void setRequiredQty(Integer requiredQty) {
        this.requiredQty = requiredQty;
    }

    public Integer getFreeQty() {
        return freeQty;
    }

    public void setFreeQty(Integer freeQty) {
        this.freeQty = freeQty;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
