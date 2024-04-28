package com.store.application.ports.output;

import com.store.application.domain.Product;

import java.util.List;

public interface ListProductsOutputPort {
    List<Product> list();
}
