package com.store.application.domain;

public enum Promos {
    BUY_X_GET_Y_FREE("BUY_X_GET_Y_FREE", "Buying two or more, the second one is free"),
    QTY_BASED_PRICE_OVERRIDE("QTY_BASED_PRICE_OVERRIDE", "Buying two or more, you get discount on the second"),
    FLAT_PERCENT("FLAT_PERCENT", "10% discount on the final order");

    private final String type;
    private final String description;

    Promos(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
