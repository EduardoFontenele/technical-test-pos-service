package com.store.adapter.repository;

import com.store.adapter.gateway.ProductsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductRepository {
    private final ProductsGateway productsGateway;

}
