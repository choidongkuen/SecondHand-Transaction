package com.example.shopproject.order.exception;

import com.example.shopproject.common.exception.AbstractException;
import com.example.shopproject.common.type.ErrorCode;

public class OrderException extends AbstractException {

    public OrderException(ErrorCode errorCode) {

        super(errorCode);
    }
}
