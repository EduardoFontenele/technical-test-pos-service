package com.store.application.ports.input;

import com.store.application.domain.OrderReceipt;
import com.store.application.domain.Product;

import java.util.List;

public interface ProcessOrderInputPort {
    OrderReceipt process(List<Product> orderItems);
}
