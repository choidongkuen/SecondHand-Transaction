package com.example.shopproject.product.exception;


import com.example.shopproject.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ProductExceptionHandler {

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<?> handleMemberException(ProductException e) {

        log.info(e.getMessage());

        return new ResponseEntity<>(new ResponseResult(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
