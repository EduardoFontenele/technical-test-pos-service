package com.store.adapter.input.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductPresenter {
    private String name;
    private String price;
    private Integer orderedQuantity;
}
