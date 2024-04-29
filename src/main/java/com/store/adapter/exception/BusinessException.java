package com.store.adapter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{
    private final HttpStatus httpStatus;
    private final String message;

    public BusinessException(ExceptionsEnum exceptionsEnum, String item) {
        this.message = exceptionsEnum.getMessage().replace("[item]", item);
        this.httpStatus = exceptionsEnum.getHttpStatus();
    }

    public BusinessException(HttpStatus httpStatus, String message) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
