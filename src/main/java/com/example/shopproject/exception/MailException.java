package com.example.shopproject.exception;

import com.example.shopproject.main.type.ErrorCode;

public class MailException extends RuntimeException{

    private ErrorCode errorcode;
    private String message;


    public MailException(ErrorCode errorcode){
        this.errorcode = errorcode;
        this.message = errorcode.getMessage();
    }
}
