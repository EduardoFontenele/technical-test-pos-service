package com.store.adapter.output.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionClientResponseDTO {
    private String id;
    private String type;
    private Integer requiredQty;
    private Integer freeQty;
    private Integer amount;
}
