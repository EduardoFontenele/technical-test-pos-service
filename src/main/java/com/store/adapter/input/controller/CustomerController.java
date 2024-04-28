package com.store.adapter.input.controller;

import com.store.config.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/customers")
public class CustomerController {
    private final Customer customer;

    @GetMapping("/accountInfo")
    public Customer getCustomer() {
        return customer;
    }
}
