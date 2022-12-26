package com.example.shopproject.common.exception;

import com.example.shopproject.common.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class AbstractException extends RuntimeException{
    private ErrorCode errorCode;
    private String message;

    public AbstractException(ErrorCode errorCode) {

        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

}
