package com.store.core.usecases;

import com.store.core.domain.Product;
import com.store.core.ports.input.ListProductsPageInputPort;
import com.store.core.ports.output.ListProductsOutputPort;

import java.util.List;

public class ListProductsPageUseCase implements ListProductsPageInputPort {

    private final ListProductsOutputPort listProductsOutputPort;

    public ListProductsPageUseCase(ListProductsOutputPort listProductsOutputPort) {
        this.listProductsOutputPort = listProductsOutputPort;
    }

    @Override
    public List<Product> list() {
        return listProductsOutputPort.list();
    }
}
