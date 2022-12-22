package com.example.shopproject.exception;

import com.example.shopproject.main.type.ErrorCode;

public class MemberException extends RuntimeException{

    private ErrorCode errorCode;
    private String message;

    public MemberException(ErrorCode errorCode){
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
