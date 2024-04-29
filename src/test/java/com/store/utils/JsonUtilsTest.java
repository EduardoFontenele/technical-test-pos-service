package com.store.utils;

import com.store.adapter.output.dto.ProductClientResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonUtilsTest {
    private String validJson;
    private String invalidJson;

    @BeforeEach
    void setUp() {
        validJson = "[{\"id\":\"abc123\",\"name\":\"Product\",\"price\":null,\"promotions\":null}]";
        invalidJson = "{\"id\":\"abc123\",\"name\":\"Product\",\"price\":null,\"promotions\":null}";
    }

    @Test
    void toListOfProductClientResponseShouldReturnEmptyListWhenJsonIsNull() {
        List<ProductClientResponseDTO> result = JsonUtils.ToListOfProductClientResponse(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void toListOfProductClientResponseShouldReturnEmptyListWhenJsonIsEmpty() {
        List<ProductClientResponseDTO> result = JsonUtils.ToListOfProductClientResponse("");
        assertTrue(result.isEmpty());
    }

    @Test
    void toListOfProductClientResponseShouldReturnListWhenJsonIsValid() {
        List<ProductClientResponseDTO> result = JsonUtils.ToListOfProductClientResponse(validJson);
        assertEquals(1, result.size());
        assertEquals("abc123", result.get(0).getId());
    }

    @Test
    void toListOfProductClientResponseShouldThrowExceptionWhenJsonIsInvalid() {
        assertThrows(RuntimeException.class, () -> JsonUtils.ToListOfProductClientResponse(invalidJson));
    }

    @Test
    void toProductClientResponseShouldReturnNullWhenJsonIsNull() {
        ProductClientResponseDTO result = JsonUtils.toProductClientResponse(null);
        assertNull(result);
    }

    @Test
    void toProductClientResponseShouldReturnNullWhenJsonIsEmpty() {
        ProductClientResponseDTO result = JsonUtils.toProductClientResponse("");
        assertNull(result);
    }

    @Test
    void toProductClientResponseShouldReturnObjectWhenJsonIsValid() {
        ProductClientResponseDTO result = JsonUtils.toProductClientResponse(invalidJson);
        assertNotNull(result);
        assertEquals("abc123", result.getId());
    }

    @Test
    void toProductClientResponseShouldReturnNullWhenJsonIsInvalid() {
        ProductClientResponseDTO result = JsonUtils.toProductClientResponse(validJson);
        assertNull(result);
    }
}