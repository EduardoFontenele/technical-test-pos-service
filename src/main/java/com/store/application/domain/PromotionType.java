package com.store.application.domain;

public enum PromotionType {
    BUY_X_GET_Y_FREE("Buy [required_quantity] or more products, get one completely for free!"),
    QTY_BASED_PRICE_OVERRIDE("Buy [required_quantity] or more products, and the first [required_quantity] will cost you only [price]!"),
    FLAT_PERCENT("Buy as many products as you want, and you'll get a discount of [percent]%!");

    private final String value;

    PromotionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
