package com.store.adapter.input.controller;

import com.store.adapter.exception.BusinessException;
import com.store.adapter.exception.ExceptionsEnum;
import com.store.adapter.input.dto.OrderProductDTO;
import com.store.adapter.input.dto.OrderReceiptPresenter;
import com.store.adapter.input.dto.ProductPresenter;
import com.store.adapter.mapper.OrderMapper;
import com.store.adapter.mapper.ProductMapper;
import com.store.core.domain.OrderReceipt;
import com.store.core.domain.Product;
import com.store.core.ports.input.ListProductsPageInputPort;
import com.store.core.ports.input.ProcessOrderInputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/products")
@RequiredArgsConstructor
public class ProductsController {
    private final ProductMapper productMapper = ProductMapper.INSTANCE;
    private final OrderMapper orderMapper = OrderMapper.INSTANCE;

    private final ListProductsPageInputPort listProductsPageInputPort;
    private final ProcessOrderInputPort processOrderInputPort;

    @GetMapping
    public ResponseEntity<List<ProductPresenter>> listAllProducts() {
        List<ProductPresenter> response = listProductsPageInputPort.list().stream()
                .map(productMapper::toProductPresenterDTO)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<OrderReceiptPresenter> orderMeal(@RequestBody List<OrderProductDTO> orderProductDTOList) {
        if (orderProductDTOList.isEmpty()) throw new BusinessException(ExceptionsEnum.EMPTY_ORDER_REQUEST);
        List<Product> orderedProducts = orderProductDTOList.stream().map(productMapper::toProduct).toList();
        OrderReceipt orderReceipt = processOrderInputPort.process(orderedProducts);
        OrderReceiptPresenter orderReceiptPresenter = orderMapper.toOrderResultPresenter(orderReceipt);

        return ResponseEntity.ok(orderReceiptPresenter);
    }
}
