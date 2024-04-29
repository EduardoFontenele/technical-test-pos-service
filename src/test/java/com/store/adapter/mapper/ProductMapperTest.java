package com.store.adapter.mapper;

import com.store.adapter.input.dto.OrderProductDTO;
import com.store.adapter.input.dto.OrderProductPresenter;
import com.store.adapter.input.dto.ProductPresenter;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.adapter.output.dto.PromotionClientResponseDTO;
import com.store.application.domain.Product;
import com.store.application.domain.Promotion;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ProductMapperTest {
    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    void toProductWithPromotionsMapsCorrectly() {
        ProductClientResponseDTO dto = new ProductClientResponseDTO();
        dto.setId("1");
        dto.setName("Product 1");
        dto.setPrice(new BigDecimal("100.00"));

        PromotionClientResponseDTO promotionDto = new PromotionClientResponseDTO();
        promotionDto.setId("1");
        promotionDto.setType("PROMO_TYPE");
        promotionDto.setRequiredQty(2);
        promotionDto.setFreeQty(1);
        promotionDto.setAmount(50);
        promotionDto.setPrice(new BigDecimal("75.00"));

        dto.setPromotions(Collections.singletonList(promotionDto));

        Product product = productMapper.toProductWithPromotions(dto);

        assertEquals("1", product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals(new BigDecimal("1.00"), product.getPrice());
        assertEquals(1, product.getPromotions().size());

        Promotion promotion = product.getPromotions().get(0);
        assertEquals("1", promotion.getId());
        assertEquals("PROMO_TYPE", promotion.getType());
        assertEquals(2, promotion.getRequiredQty());
        assertEquals(1, promotion.getFreeQty());
        assertEquals(50, promotion.getAmount());
        assertEquals(new BigDecimal("0.75"), promotion.getPrice());
    }

    @Test
    void toOrderProductPresenterMapsCorrectly() {
        Product product = new Product();
        product.setId("1");
        product.setName("Product 1");
        product.setPrice(new BigDecimal("100.00"));
        product.setOrderedQuantity(2);
        product.setPromoApplied("Some promotion");

        OrderProductPresenter presenter = productMapper.toOrderProductPresenter(product);

        assertEquals("Product 1", presenter.getName());
        assertEquals("$100.00", presenter.getPrice());
        assertEquals("Some promotion", presenter.getPromoApplied());
        assertEquals(2, presenter.getOrderedQuantity());
    }

    @Test
    void toProductPresenterDTOMapsCorrectly() {
        Product product = new Product();
        product.setId("1");
        product.setName("Product 1");
        product.setPrice(new BigDecimal("100.00"));

        ProductPresenter presenter = productMapper.toProductPresenterDTO(product);

        assertEquals("1", presenter.getId());
        assertEquals("Product 1", presenter.getName());
        assertEquals("$100.00", presenter.getPrice());
    }

    @Test
    void toProductMapsCorrectlyFromOrderProductDTO() {
        OrderProductDTO dto = new OrderProductDTO();
        dto.setProductId("1");
        dto.setQuantity(2);

        Product product = productMapper.toProduct(dto);

        assertEquals("1", product.getId());
        assertEquals(2, product.getOrderedQuantity());
    }

    @Test
    void toProductMapsCorrectlyFromOrderProductPresenter() {
        OrderProductPresenter presenter = new OrderProductPresenter();
        presenter.setName("Product 1");
        presenter.setOrderedQuantity(2);

        Product product = productMapper.toProduct(presenter);

        assertEquals("Product 1", product.getName());
        assertEquals(2, product.getOrderedQuantity());
    }
}