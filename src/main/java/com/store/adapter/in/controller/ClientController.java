package com.store.adapter.in.controller;

import com.store.adapter.out.ProductsClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ClientController {

    private final ProductsClient productsClient;

    @GetMapping
    public ResponseEntity<String> getAllProducts() {
        return productsClient.listALl();
    }
}
