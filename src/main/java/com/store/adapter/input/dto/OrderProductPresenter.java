package com.store.adapter.input.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderProductPresenter {
    private String name;
    private String price;
    private Integer orderedQuantity;
    private String promoApplied;
}
