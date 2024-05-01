package com.store.adapter.output.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.adapter.mapper.ProductMapper;
import com.store.adapter.output.clients.ProductClient;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.application.core.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ListProductsOutputAdapterTest {
    @Mock
    private ProductClient productClient;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ListProductsOutputAdapter listProductsOutputAdapter;

    @Test
    void listReturnsListOfProductsWhenClientResponseIsNotNull() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId("abc123");
        product.setName("Product");
        product.setPrice(new BigDecimal("10.00"));
        products.add(product);
        product.setPromotions(new ArrayList<>());

        List<ProductClientResponseDTO> responseDTOS = new ArrayList<>();
        ProductClientResponseDTO responseDTO = new ProductClientResponseDTO();
        responseDTO.setId("abc123");
        responseDTO.setName("Product");
        responseDTO.setPrice(new BigDecimal("10.00"));
        responseDTOS.add(responseDTO);

        String jsonString = objectMapper.writeValueAsString(responseDTOS);

        given(productClient.getAllProducts()).willReturn(ResponseEntity.ok(jsonString));

        List<Product> result = listProductsOutputAdapter.list();

        assertNotNull(result);
        assertEquals(products.get(0).getId(), result.get(0).getId());
        assertEquals(products.get(0).getName(), result.get(0).getName());
    }

    @Test
    void listReturnsEmptyListWhenClientResponseIsEmpty() {
        when(productClient.getAllProducts()).thenReturn(ResponseEntity.ok().build());

        List<Product> result = listProductsOutputAdapter.list();

        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void listShouldThrowException() {
        given(productClient.getAllProducts()).willReturn(null);

        // Act and Assert
        Exception exception = assertThrows(Exception.class, () -> listProductsOutputAdapter.list());
    }

}