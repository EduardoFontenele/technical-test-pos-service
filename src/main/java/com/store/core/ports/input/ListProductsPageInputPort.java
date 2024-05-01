package com.store.core.ports.input;

import com.store.core.domain.Product;

import java.util.List;

public interface ListProductsPageInputPort {
    List<Product> list();
}
