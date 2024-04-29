package com.store.adapter.input.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class OrderReceiptPresenter {
    private String finalPrice;
    private String priceWithDiscount;
    private String totalSaved;
    private List<OrderProductPresenter> orderedProducts;
}
