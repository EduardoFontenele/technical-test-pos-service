package com.store.adapter.input.config;

import com.store.adapter.output.clients.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BootstrapApplication {

    private final ProductClient productClient;
}
