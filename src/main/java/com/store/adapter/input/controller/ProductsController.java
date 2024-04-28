package com.store.adapter.input.controller;

import com.store.adapter.input.dto.ProductPresenterDTO;
import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.application.ports.input.ListProductsInputPort;
import com.store.config.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ListProductsInputPort listProductsInputPort;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final User user;

    @GetMapping
    public ResponseEntity<List<ProductPresenterDTO>> listAllProducts() {
        List<ProductPresenterDTO> response = listProductsInputPort.list().stream()
                .map(productMapper::toProductPresenterDTO)
                .toList();
        return ResponseEntity.ok(response);
    }
}
