package com.example.shopproject.common.type;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 상품 판매 상태 (핀매중 or 예약중 or 판매완료)
 */

@RequiredArgsConstructor
@Getter
public enum ProductSaleStatus {

    ON_SALE("판매 상태"),
    RESERVED("예약 상태"),
    SOLD_OUT("판매 완료 상태");

    private final String message;
}

