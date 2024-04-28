package com.store.application.usecase;

import com.store.application.domain.Product;
import com.store.application.ports.in.ListProductsInputPort;
import com.store.application.ports.out.ListProductsOutputPort;

import java.util.List;

public class ListProductsUseCase implements ListProductsInputPort {
    private final ListProductsOutputPort listProductsOutputPort;

    public ListProductsUseCase(ListProductsOutputPort listProductsOutputPort) {
        this.listProductsOutputPort = listProductsOutputPort;
    }

    @Override
    public List<Product> execute() {
        return listProductsOutputPort.execute();
    }
}
