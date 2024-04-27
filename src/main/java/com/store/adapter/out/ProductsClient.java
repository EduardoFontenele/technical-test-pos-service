package com.store.adapter.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "http://localhost:8081", name = "productsClient")
public interface ProductsClient {

    @GetMapping("/products")
    ResponseEntity<String> listALl();
}
