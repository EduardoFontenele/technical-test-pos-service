package com.store.adapter.mapper;

import com.store.adapter.dto.GetProductResponseDto;
import com.store.application.domain.Product;

import java.math.BigDecimal;

public class ProductMapper {
    public static GetProductResponseDto toGetProductResponseDto(Product product) {
        return new GetProductResponseDto(product.getId(), product.getName(), product.getPrice().doubleValue());
    }

    public static Product fromProductRequestDto(GetProductResponseDto productRequestDto) {
        return new Product(productRequestDto.getId(), productRequestDto.getName(), BigDecimal.valueOf(productRequestDto.getPrice()));
    }

}
