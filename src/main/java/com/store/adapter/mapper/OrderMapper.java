package com.store.adapter.mapper;

import com.store.adapter.input.dto.OrderProductPresenter;
import com.store.adapter.input.dto.OrderReceiptPresenter;
import com.store.application.domain.OrderReceipt;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class OrderMapper {
    public static final OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);
    private static final ProductMapper productMapper = ProductMapper.INSTANCE;

    public static OrderReceiptPresenter toOrderResultPresenter(OrderReceipt orderReceipt) {
        OrderReceiptPresenter orderReceiptPresenter = new OrderReceiptPresenter();

        orderReceiptPresenter.setFinalPrice(orderReceipt.getFinalPrice());
        orderReceiptPresenter.setTotalSaved(orderReceipt.getTotalSaved());

        List<OrderProductPresenter> orderedProducts = orderReceipt.getOrderedProducts().stream()
                .map(productMapper::toOrderProductPresenter)
                .toList();

        orderReceiptPresenter.setOrderedProducts(orderedProducts);

        return orderReceiptPresenter;
    }
}
