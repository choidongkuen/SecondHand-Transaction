package com.example.shopproject.member.exception;


import com.example.shopproject.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class MemberExceptionHandler {

    @ExceptionHandler(MemberException.class)
    protected ResponseEntity<?> handleMemberException(MemberException e){

        log.info(e.getMessage());

        return new ResponseEntity<>(
                new ResponseResult(false, e.getMessage()), HttpStatus.BAD_REQUEST
        );
    }
}
