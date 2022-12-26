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


/**
 * 0. 등록된 상품 리스트 검색
 * 1. 관리자 상품 등록
 * 2. 판매자 상품 등록
 * 3. 관리자 상품 쿠폰 설정
 * 4. 관리자 상품 상세 설명 등록
 * 5. 판매자 상품 상세 설명 등록
 * 6. 관리자 상품 상태 변경
 * 7. 판매자 상품 상태 변경
 * 8. 관리자 상품 삭제
 * 9. 판매자 상품 삭제
 *
 */


