package com.example.shopproject.comment.exception;

import com.example.shopproject.common.exception.AbstractException;
import com.example.shopproject.common.type.ErrorCode;

public class CommentException extends AbstractException {

    public CommentException(ErrorCode errorCode) {

        super(errorCode);
    }

}
