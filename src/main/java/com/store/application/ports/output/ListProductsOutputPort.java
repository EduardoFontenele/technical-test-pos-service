package com.store.application.ports.output;

import com.store.application.core.domain.Product;

import java.util.List;

public interface ListProductsOutputPort {
    List<Product> list();
}
