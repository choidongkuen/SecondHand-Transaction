package com.example.shopproject.member.exception;

import com.example.shopproject.common.exception.AbstractException;
import com.example.shopproject.common.type.ErrorCode;

public class MemberException extends AbstractException {

    public MemberException(ErrorCode errorCode) {

        super(errorCode);
    }

}
