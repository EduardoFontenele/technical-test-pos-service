package com.store.application.core.usecases;

import com.store.application.core.domain.Product;
import com.store.application.ports.input.ListProductsPageInputPort;
import com.store.application.ports.output.ListProductsOutputPort;

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
