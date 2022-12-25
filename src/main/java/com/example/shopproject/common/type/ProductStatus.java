package com.example.shopproject.common.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum ProductStatus {

    USED("중고 상품"),
    NEW("새 상품");

    private final String message;

}
