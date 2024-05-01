package com.store.application.core.domain;

public enum PromotionType {
    BUY_X_GET_Y_FREE("Buy [required_quantity] or more products, get one completely for free!"),
    QTY_BASED_PRICE_OVERRIDE("Buy [required_quantity] or more products, and the first [required_quantity] will cost you only [price]!"),
    FLAT_PERCENT("Buy as many products as you want, and you'll get a discount of [percent]%!");

    private final String description;

    PromotionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
