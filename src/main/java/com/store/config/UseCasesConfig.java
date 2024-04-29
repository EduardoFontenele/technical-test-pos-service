package com.store.config;

import com.store.application.ports.input.FindProductFullInformationOutputPort;
import com.store.application.ports.input.ListProductsPageInputPort;
import com.store.application.ports.input.ProcessOrderInputPort;
import com.store.application.ports.output.ListProductsOutputPort;
import com.store.application.usecases.ListProductsPageUseCase;
import com.store.application.usecases.ProcessOrderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesConfig {

    @Bean
    public ListProductsPageInputPort listProductsInputPort(ListProductsOutputPort listProductsOutputPort) {
        return new ListProductsPageUseCase(listProductsOutputPort);
    }

    @Bean
    public ProcessOrderInputPort processOrderInputPort(FindProductFullInformationOutputPort findProductFullInformationOutputPort) {
        return new ProcessOrderUseCase(findProductFullInformationOutputPort);
    }
}
