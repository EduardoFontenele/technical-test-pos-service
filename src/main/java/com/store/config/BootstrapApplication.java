package com.store.config;

import com.store.adapter.dto.GetProductResponseDto;
import com.store.adapter.gateway.ProductsGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BootstrapApplication {
    private final ProductsGateway productsGateway;

    @Bean
    List<GetProductResponseDto> getProductResponseDtoList() {
        return productsGateway.listALl();
    }
}
