package com.store.adapter.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ExceptionsEnum {
    INVALID_PRODUCT_ID(HttpStatus.NOT_FOUND, "Product with ID '[item]' not found."),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "Invalid operation. Please, try again.");

    private final HttpStatus httpStatus;
    private final String message;
}
