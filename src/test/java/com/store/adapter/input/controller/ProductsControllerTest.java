package com.store.adapter.input.controller;

import com.store.adapter.exception.BusinessException;
import com.store.adapter.input.dto.OrderProductDTO;
import com.store.adapter.input.dto.OrderReceiptPresenter;
import com.store.adapter.input.dto.ProductPresenter;
import com.store.application.domain.OrderReceipt;
import com.store.application.domain.Product;
import com.store.application.ports.input.ListProductsPageInputPort;
import com.store.application.ports.input.ProcessOrderInputPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProductsControllerTest {
    @Mock
    private ListProductsPageInputPort listProductsPageInputPort;

    @Mock
    private ProcessOrderInputPort processOrderInputPort;

    @InjectMocks
    private ProductsController productsController;

    @Test
    void shouldListAllAvailableProducts() {
        List<Product> productList = new ArrayList<>();
        Product product = new Product();
        product.setId("abc123");
        product.setName("Product");
        product.setPrice(new BigDecimal("10.00"));

        productList.add(product);

        given(listProductsPageInputPort.list()).willReturn(productList);

        ResponseEntity<List<ProductPresenter>> result = productsController.listAllProducts();

        assertNotNull(result);
        assertEquals("abc123", result.getBody().get(0).getId());
        assertEquals("Product", result.getBody().get(0).getName());
        assertEquals("$10.00", result.getBody().get(0).getPrice());
    }

    @Test
    void shouldSuccessfullyMakeAnOrder() {
        List<Product> orderedProducts = new ArrayList<>();
        orderedProducts.add(Product.builder()
                        .id("abc123")
                        .name("Product")
                        .orderedQuantity(1)
                        .price(new BigDecimal("10.00"))
                .build());

        OrderReceipt orderReceipt = new OrderReceipt();
        orderReceipt.setFinalPrice(new BigDecimal("10.00"));
        orderReceipt.setPriceWithDiscount(new BigDecimal("10.00"));
        orderReceipt.setTotalSaved(new BigDecimal("0.00"));
        orderReceipt.setOrderedProducts(orderedProducts);

        given(processOrderInputPort.process(anyList())).willReturn(orderReceipt);

        ResponseEntity<OrderReceiptPresenter> response = productsController.orderMeal(List.of(new OrderProductDTO("abc123", 1)));

        assertNotNull(response.getBody());
    }

    @Test
    void shouldThrowExceptionWhenOrderIsEmpty() {
        List<OrderProductDTO> emptyOrderProductDTOList = new ArrayList<>();

        assertThrows(BusinessException.class, () -> productsController.orderMeal(emptyOrderProductDTOList));
    }
}