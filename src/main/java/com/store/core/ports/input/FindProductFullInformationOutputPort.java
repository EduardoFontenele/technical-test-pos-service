package com.store.core.ports.input;

import com.store.core.domain.Product;

public interface FindProductFullInformationOutputPort {
    Product findProduct(String productId);
}
