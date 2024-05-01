package com.store.application.ports.input;

import com.store.application.core.domain.Product;

import java.util.List;

public interface ListProductsPageInputPort {
    List<Product> list();
}
