package com.store.config;

import com.store.application.ports.input.ListProductsInputPort;
import com.store.application.ports.output.ListProductsOutputPort;
import com.store.application.usecases.ListProductsUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public ListProductsInputPort listProductsInputPort(ListProductsOutputPort listProductsOutputPort) {
        return new ListProductsUseCase(listProductsOutputPort);
    }
}
