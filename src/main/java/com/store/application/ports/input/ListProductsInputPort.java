package com.store.application.ports.input;

import com.store.application.domain.Product;

import java.util.List;

public interface ListProductsInputPort {
    List<Product> list();
}
