package com.store.adapter.mapper;

import com.store.adapter.input.dto.OrderProductDTO;
import com.store.adapter.input.dto.OrderProductPresenter;
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
    @Mapping(target = "price", source = "price", qualifiedByName = "convertPenniesToDollars")
    public abstract Product toProduct(ProductClientResponseDTO productClientResponseDTO);

    @Mapping(target = "id", source = "productId")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "orderedQuantity", source = "quantity")
    @Mapping(target = "promotions", ignore = true)
    public abstract Product toProduct(OrderProductDTO orderProductDTO);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "orderedQuantity", source = "orderedQuantity")
    @Mapping(target = "promotions", ignore = true)
    public abstract Product toProduct(OrderProductPresenter orderProductPresenter);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price")
    public abstract ProductClientResponseDTO toProductClientResponse(Product product);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price", qualifiedByName = "convertPenniesToDollars")
    @Mapping(target = "promotions", source = "promotions")
    public abstract Product toProductWithPromotions(ProductClientResponseDTO productClientResponseDTO);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    public abstract ProductPresenter toProductPresenterDTO(Product product);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "price", source = "price", qualifiedByName = "formatPrice")
    @Mapping(target = "orderedQuantity", source = "orderedQuantity")
    public abstract OrderProductPresenter toOrderProductPresenter(Product product);

    @Named("formatPrice")
    public String formatPrice(BigDecimal price) {
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);
        format.setMinimumFractionDigits(2);
        return format.format(price);
    }

    @Named("convertPenniesToDollars")
    public BigDecimal convertPenniesToDollars(BigDecimal price) {
        return price.divide(new BigDecimal(100));
    }
}
