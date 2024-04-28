package com.store.adapter.gateway;

import com.store.adapter.dto.GetProductResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://localhost:8081/products", name = "productsClient")
public interface ProductsGateway {

    @GetMapping
    List<GetProductResponseDto> listALl();

    @GetMapping("/{productId}")
    GetProductResponseDto getProductWithPromos(@PathVariable String productId);
}
