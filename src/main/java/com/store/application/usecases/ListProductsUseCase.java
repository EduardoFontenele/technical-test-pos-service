package com.store.application.usecases;

import com.store.application.domain.Product;
import com.store.application.ports.input.ListProductsInputPort;
import com.store.application.ports.output.ListProductsOutputPort;

import java.util.List;

public class ListProductsUseCase implements ListProductsInputPort {

    private final ListProductsOutputPort listProductsOutputPort;

    public ListProductsUseCase(ListProductsOutputPort listProductsOutputPort) {
        this.listProductsOutputPort = listProductsOutputPort;
    }

    @Override
    public List<Product> list() {
        return listProductsOutputPort.list();
    }
}
