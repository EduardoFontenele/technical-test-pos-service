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
    private BigDecimal finalPrice;
    private BigDecimal totalSaved;
    private List<OrderProductPresenter> orderedProducts;
}
