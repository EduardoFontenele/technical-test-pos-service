package com.store.adapter.input.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class OrderReceiptPresenter {
    private String priceWithoutDiscount;
    private String priceWithDiscount;
    private String moneySaved;
    private List<OrderProductPresenter> orderedProducts;
}
