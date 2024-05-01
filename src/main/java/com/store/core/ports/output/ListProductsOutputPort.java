package com.store.core.ports.output;

import com.store.core.domain.Product;

import java.util.List;

public interface ListProductsOutputPort {
    List<Product> list();
}
