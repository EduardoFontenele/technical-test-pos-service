package com.store.config;

import com.store.application.ports.in.ListProductsInputPort;
import com.store.application.ports.out.ListProductsOutputPort;
import com.store.application.usecase.ListProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {
    @Bean
    public ListProductsInputPort listProductsInputPort(ListProductsOutputPort listProductsOutputPort) {
        return new ListProductsUseCase(listProductsOutputPort);
    }
}
