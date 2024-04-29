package com.store.adapter.output.services;

import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.output.clients.ProductClient;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.application.domain.Product;
import com.store.application.ports.input.FindProductFullInformationOutputPort;
import com.store.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FindProductFullInformationOutputAdapter implements FindProductFullInformationOutputPort {

    private final ProductClient productClient;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public Product findProduct(String productId) {
        try {
            ResponseEntity<String> clientResponse = productClient.getProductFullInformationById(productId);
            if (clientResponse.getBody() == null) throw new NoSuchElementException("Product doesn't exist or ID is wrong");

            ProductClientResponseDTO productDto = JsonUtils.toProductClientResponse(clientResponse.getBody());
            return productMapper.toProductWithPromotions(productDto);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
