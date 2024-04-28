package com.store.application.ports.out;

import com.store.application.domain.Product;

import java.util.List;

public interface ListProductsOutputPort {
    List<Product> execute();
}
