package com.store.adapter.output.services;

import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.output.clients.ProductClient;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.application.domain.Product;
import com.store.application.ports.output.ListProductsOutputPort;
import com.store.config.User;
import com.store.utils.JsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ListProductsOutputAdapter implements ListProductsOutputPort {
    private final ProductClient productClient;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Override
    public List<Product> list() {
        List<Product> result = new ArrayList<>();

        try {
            ResponseEntity<String> clientResponse = productClient.getAllProducts();
            String responseAsString = clientResponse.getBody();
            List<ProductClientResponseDTO> productsResponseList = JsonUtils.ToListOfProductClientResponse(responseAsString);

            if (productsResponseList == null) return result;

            for (ProductClientResponseDTO productClientResponseDTO : productsResponseList) {
                result.add(productMapper.toProduct(productClientResponseDTO));
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }


        return result;
    }

}
