package com.store.config;

import com.store.core.ports.input.FindProductFullInformationOutputPort;
import com.store.core.ports.input.ListProductsPageInputPort;
import com.store.core.ports.input.ProcessOrderInputPort;
import com.store.core.ports.output.ListProductsOutputPort;
import com.store.core.usecases.ListProductsPageUseCase;
import com.store.core.usecases.ProcessOrderUseCase;
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
