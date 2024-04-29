package com.store.adapter.output.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductClientResponseDTO {
    private String id;
    private String name;
    private BigDecimal price;
    private List<PromotionClientResponseDTO> promotions;
}
