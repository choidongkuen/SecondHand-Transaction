package com.example.shopproject.category.exception;

import com.example.shopproject.common.exception.AbstractException;
import com.example.shopproject.common.type.ErrorCode;

public class CategoryException extends AbstractException {

    public CategoryException(ErrorCode errorCode){

        super(errorCode);
    }

}
