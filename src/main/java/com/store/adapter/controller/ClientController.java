package com.store.adapter.controller;

import com.store.adapter.gateway.ProductsGateway;
import com.store.adapter.dto.GetProductResponseDto;
import com.store.adapter.mapper.ProductMapper;
import com.store.application.domain.Product;
import com.store.application.ports.in.ListProductsInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ClientController {

    private final ListProductsInputPort listProductsInputPort;
    private final List<GetProductResponseDto> menu;
    private final ProductsGateway productsGateway;

    @GetMapping
    public ResponseEntity<List<GetProductResponseDto>> getMenu() {
        return ResponseEntity.ok(menu);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<GetProductResponseDto> getProductWithPromos(@PathVariable String productId) {
        return ResponseEntity.ok(productsGateway.getProductWithPromos(productId));
    }
}
