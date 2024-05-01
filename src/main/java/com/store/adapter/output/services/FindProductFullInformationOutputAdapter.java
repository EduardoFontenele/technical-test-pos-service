package com.store.adapter.output.services;

import com.store.adapter.exception.BusinessException;
import com.store.adapter.exception.ExceptionsEnum;
import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.output.clients.ProductClient;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.core.domain.Product;
import com.store.core.ports.input.FindProductFullInformationOutputPort;
import com.store.utils.JsonUtils;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
            if (productDto != null && null == productDto.getPrice())
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, "Required field {} is null".replace("{}", "'price'"));

            return productMapper.toProductWithPromotions(productDto);
        } catch (FeignException ex) {
            if (ex.getMessage().contains("404")) {
                throw new BusinessException(ExceptionsEnum.INVALID_PRODUCT_ID, productId);
            } else {
                throw new BusinessException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
            }
        }
    }
}
