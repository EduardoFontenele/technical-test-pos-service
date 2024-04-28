package com.store.adapter.service;

import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.gateway.ProductsGateway;
import com.store.adapter.dto.GetProductResponseDto;
import com.store.application.domain.Product;
import com.store.application.ports.out.ListProductsOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ListProductsAdapter implements ListProductsOutputPort {

    private final ProductsGateway productsGateway;

    @Override
    public List<Product> execute() {
        List<GetProductResponseDto> response = productsGateway.listALl();
        return Objects.requireNonNull(response).stream().map(ProductMapper::fromProductRequestDto).toList();
    }
}
