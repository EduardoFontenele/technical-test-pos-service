package com.store.adapter.exception;

import com.store.application.domain.Product;
import com.store.application.ports.output.ListProductsOutputPort;
import com.store.application.usecases.ListProductsPageUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class BusinessControllerAdviceTest {
    @Mock
    private ListProductsOutputPort listProductsOutputPort;

    private ListProductsPageUseCase listProductsPageUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listProductsPageUseCase = new ListProductsPageUseCase(listProductsOutputPort);
    }

    @Test
    void listProductsReturnsAllProducts() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> products = Arrays.asList(product1, product2);

        when(listProductsOutputPort.list()).thenReturn(products);

        List<Product> result = listProductsPageUseCase.list();

        assertEquals(products, result);
    }

    @Test
    void listProductsReturnsEmptyListWhenNoProducts() {
        when(listProductsOutputPort.list()).thenReturn(Arrays.asList());

        List<Product> result = listProductsPageUseCase.list();

        assertEquals(0, result.size());
    }

}