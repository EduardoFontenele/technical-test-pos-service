package com.store.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.adapter.exception.ExceptionsEnum;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Slf4j
public final class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<ProductClientResponseDTO> ToListOfProductClientResponse(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return Collections.emptyList();
            }
            return objectMapper.readValue(json, new TypeReference<>() {});
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(ExceptionsEnum.IO_EXCEPTION.getMessage().replace("{}", e.getMessage()));
        }
    }

    public static ProductClientResponseDTO toProductClientResponse(String json) {
        try {
            if (json == null || json.isEmpty()) {
                return null;
            }
            return objectMapper.readValue(json, ProductClientResponseDTO.class);
        } catch (IOException e) {
            log.error("Error parsing JSON to ProductClientResponseDTO: {}", e.getMessage());
            return null;
        }
    }
}
