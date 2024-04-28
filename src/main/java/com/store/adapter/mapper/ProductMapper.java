package com.store.adapter.mapper;

import com.store.adapter.input.dto.ProductPresenter;
import com.store.adapter.output.dto.ProductClientResponseDTO;
import com.store.application.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Mapper
public abstract class ProductMapper {
    public static final ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    public abstract ProductClientResponseDTO toProductClientResponse(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    public abstract Product toProduct(ProductClientResponseDTO productClientResponseDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    public abstract ProductPresenter toProductPresenterDTO(Product product);

    @Named("formatPrice")
    protected String formatPrice(BigDecimal price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);
        return format.format(price.divide(BigDecimal.valueOf(100)));
    }
}
