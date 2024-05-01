package com.store.application.usecases;

import com.store.application.core.domain.OrderReceipt;
import com.store.application.core.domain.Product;
import com.store.application.core.domain.Promotion;
import com.store.application.core.usecases.ProcessOrderUseCase;
import com.store.application.ports.input.FindProductFullInformationOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProcessOrderUseCaseTest {
    private FindProductFullInformationOutputPort findProductFullInformationOutputPort;
    private ProcessOrderUseCase processOrderUseCase;

    @BeforeEach
    void setUp() {
        findProductFullInformationOutputPort = Mockito.mock(FindProductFullInformationOutputPort.class);
        processOrderUseCase = new ProcessOrderUseCase(findProductFullInformationOutputPort);
    }

    @Test
    void processOrderWithoutPromotion() {
        Product product = new Product();
        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(1);
        product.setPromotions(new ArrayList<>());

        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("100.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("0"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithFlatPercentPromotion() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("FLAT_PERCENT");
        promotion.setAmount(10);

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(1);
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("100.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("90.00"), receipt.getPriceWithDiscount().round(new MathContext(4)));
        assertEquals(new BigDecimal("10.00"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithBuyXGetYFreePromotion() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("BUY_X_GET_Y_FREE");
        promotion.setRequiredQty(2);

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(3);
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("300.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("200.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("100.00"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithQtyBasedPriceOverridePromotion() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("QTY_BASED_PRICE_OVERRIDE");
        promotion.setRequiredQty(2);
        promotion.setPrice(new BigDecimal("150.00"));

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(3);
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("300.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("250.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("50.00"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithPromotionButQuantityLessThanRequired() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("QTY_BASED_PRICE_OVERRIDE");
        promotion.setRequiredQty(2);
        promotion.setPrice(new BigDecimal("150.00"));

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(1); // Quantity less than required for promotion
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("100.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("100.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("0"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithPromotionAndQuantityEqualToRequired() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("QTY_BASED_PRICE_OVERRIDE");
        promotion.setRequiredQty(2);
        promotion.setPrice(new BigDecimal("150.00"));

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(2); // Quantity equal to required for promotion
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("200.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("150.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("50.00"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithPromotionAndQuantityMoreThanRequired() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("QTY_BASED_PRICE_OVERRIDE");
        promotion.setRequiredQty(2);
        promotion.setPrice(new BigDecimal("150.00"));

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(3);
        product.setPromotions(List.of(promotion));
        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("250.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("300.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("50.00"), receipt.getTotalSaved());
    }

    @Test
    void processOrderWithBuyXGetYFreePromotionButQuantityLessThanRequired() {
        Product product = new Product();
        Promotion promotion = new Promotion();

        promotion.setType("BUY_X_GET_Y_FREE");
        promotion.setRequiredQty(3); // Required quantity more than ordered quantity

        product.setId("C8GDyLrHJb");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(2); // Quantity less than required for promotion
        product.setPromotions(List.of(promotion));

        when(findProductFullInformationOutputPort.findProduct("C8GDyLrHJb")).thenReturn(product);

        OrderReceipt receipt = processOrderUseCase.process(Collections.singletonList(product));

        assertEquals(new BigDecimal("200.00"), receipt.getFinalPrice());
        assertEquals(new BigDecimal("200.00"), receipt.getPriceWithDiscount());
        assertEquals(new BigDecimal("0"), receipt.getTotalSaved());
    }
}