package com.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;


@Configuration
public class BootstrapApp {
    @Bean
    public User user() {
        return User.builder()
                .username("Eduardo")
                .balance(new BigDecimal(100))
                .build();
    }
}
