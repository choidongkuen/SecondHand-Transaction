package com.example.shopproject.category.exception;


import com.example.shopproject.common.response.ResponseResult;
import com.example.shopproject.product.exception.ProductException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class CategoryExceptionHandler {

    @ExceptionHandler(ProductException.class)
    protected ResponseEntity<?> handleMemberException(ProductException e) {

        log.info(e.getMessage());

        return new ResponseEntity<>(new ResponseResult(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
