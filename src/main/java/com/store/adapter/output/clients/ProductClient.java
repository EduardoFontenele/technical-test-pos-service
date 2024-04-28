package com.store.adapter.output.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productClient", url = "http://localhost:8081/products")
public interface ProductClient {
    @GetMapping
    ResponseEntity<String> getAllProducts();

    @GetMapping("/{productId}")
    ResponseEntity<String> getProductWithAvailablePromotions(@PathVariable String productId);
}
