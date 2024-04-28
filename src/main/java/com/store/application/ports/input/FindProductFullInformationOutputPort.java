package com.store.application.ports.input;

import com.store.application.domain.Product;

public interface FindProductFullInformationOutputPort {
    Product findProduct(String productId);
}
