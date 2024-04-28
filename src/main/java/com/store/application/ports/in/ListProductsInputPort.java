package com.store.application.ports.in;

import com.store.application.domain.Product;

import java.util.List;

public interface ListProductsInputPort {
    List<Product> execute();
}
