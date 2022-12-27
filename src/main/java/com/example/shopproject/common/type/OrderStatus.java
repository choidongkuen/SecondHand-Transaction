package com.example.shopproject.common.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    ORDER("주문 상태"),
    CANCEL("주문 취소 상태");

    private final String message;

}
