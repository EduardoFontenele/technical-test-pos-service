package com.store.adapter.output.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("required_qty")
    private Integer requiredQty;

    @JsonProperty("free_qty")
    private Integer freeQty;
    private Integer amount;
}
