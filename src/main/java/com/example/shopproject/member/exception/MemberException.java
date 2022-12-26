package com.example.shopproject.member.exception;

import com.example.shopproject.common.type.ErrorCode;

public class MemberException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public MemberException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}
