package com.store.core.ports.input;

import com.store.core.domain.OrderReceipt;
import com.store.core.domain.Product;

import java.util.List;

public interface ProcessOrderInputPort {
    OrderReceipt process(List<Product> orderItems);
}
