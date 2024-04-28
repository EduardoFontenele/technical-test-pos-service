package com.store.config;

import com.store.adapter.input.dto.ProductPresenter;
import com.store.adapter.mapper.ProductMapper;
import com.store.application.ports.input.ListProductsInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;


@Configuration
@RequiredArgsConstructor
public class BootstrapApp {
    private final ListProductsInputPort listProductsInputPort;
    private final ProductMapper productMapper = ProductMapper.INSTANCE;

    @Bean
    public Customer user() {
        return Customer.builder()
                .username("Eduardo")
                .balance(new BigDecimal(100))
                .build();
    }

    @Bean
    public List<ProductPresenter> productPresenters() {
        return listProductsInputPort.list().stream()
                .map(productMapper::toProductPresenterDTO)
                .toList();
    }
}
