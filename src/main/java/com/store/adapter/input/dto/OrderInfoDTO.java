package com.store.adapter.input.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderInfoDTO {
    private String productId;
    private Integer quantity;
}
