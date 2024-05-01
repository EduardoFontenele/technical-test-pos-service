package com.store.application.ports.input;

import com.store.application.core.domain.OrderReceipt;
import com.store.application.core.domain.Product;

import java.util.List;

public interface ProcessOrderInputPort {
    OrderReceipt process(List<Product> orderItems);
}
