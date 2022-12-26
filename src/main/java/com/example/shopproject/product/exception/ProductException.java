package com.example.shopproject.product.exception;

import com.example.shopproject.common.exception.AbstractException;
import com.example.shopproject.common.type.ErrorCode;

public class ProductException extends AbstractException {

    public ProductException(ErrorCode errorCode) {

        super(errorCode);
    }

}
