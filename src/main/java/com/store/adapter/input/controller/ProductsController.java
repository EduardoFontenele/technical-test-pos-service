package com.store.adapter.input.controller;

import com.store.adapter.input.dto.OrderInfoDTO;
import com.store.adapter.input.dto.OrderResultPresenter;
import com.store.adapter.input.dto.ProductPresenter;
import com.store.adapter.mapper.ProductMapper;
import com.store.config.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final List<ProductPresenter> productPresenters;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final Customer customer;

    @GetMapping
    public ResponseEntity<List<ProductPresenter>> listAllProducts() {
        return ResponseEntity.ok(productPresenters);
    }

    @PostMapping
    public ResponseEntity<OrderResultPresenter> orderMeal(@RequestBody List<OrderInfoDTO> orderInfoDTOList) {
        return ResponseEntity.ok(OrderResultPresenter.builder().customer("Eduardo Fontenele").build());
    }
}
