package com.store.adapter.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionsEnum {
    INVALID_PRODUCT_ID(HttpStatus.NOT_FOUND, "Product with ID '[item]' not found."),
    IO_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "{}"),
    REQUIRED_FIELD_IS_NULL(HttpStatus.INTERNAL_SERVER_ERROR, "{}"),
    EMPTY_ORDER_REQUEST(HttpStatus.BAD_REQUEST, "The order has 0 itens"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid operation. Please, try again.");

    private final HttpStatus httpStatus;
    private final String message;
}
