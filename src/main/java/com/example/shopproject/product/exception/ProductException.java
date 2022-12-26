package com.example.shopproject.product.exception;

import com.example.shopproject.common.type.ErrorCode;

public class ProductException extends RuntimeException {

    private ErrorCode errorCode;

    private String message;

    public ProductException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
