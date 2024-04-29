package com.store.adapter.output.services;

import com.store.adapter.exception.BusinessException;
import com.store.adapter.output.clients.ProductClient;
import com.store.application.domain.Product;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FindProductFullInformationOutputAdapterTest {
    @Mock
    private ProductClient productClient;

    @InjectMocks
    private FindProductFullInformationOutputAdapter findProductFullInformationOutputAdapter;


    @Test
    void findProductShouldReturnProductWhenProductExists() {
        String productId = "abc123";
        String productJson = "{\"id\":\"abc123\",\"name\":\"Product\",\"price\":15,\"promotions\":null}";
        when(productClient.getProductFullInformationById(productId)).thenReturn(ResponseEntity.ok(productJson));

        Product product = findProductFullInformationOutputAdapter.findProduct(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
    }

    @Test
    void findProductShouldThrowExceptionWhenProductDoesNotExist() {
        String productId = "abc123";
        when(productClient.getProductFullInformationById(productId)).thenReturn(ResponseEntity.ok(null));

        assertThrows(NoSuchElementException.class, () -> findProductFullInformationOutputAdapter.findProduct(productId));
    }

    @Test
    void findProductShouldThrowBusinessException() {
        String productId = "abc123";
        String productJson = "{\"id\":\"abc123\",\"name\":\"Product\",\"price\":null,\"promotions\":null}";

        given(productClient.getProductFullInformationById(productId)).willReturn(ResponseEntity.ok(productJson));
        assertThrows(BusinessException.class, () -> findProductFullInformationOutputAdapter.findProduct(productId));
    }
}