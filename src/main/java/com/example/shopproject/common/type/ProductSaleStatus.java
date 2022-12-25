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
 * 1. 회원 이메일
 * 2. 회원 이름
 * 3. 회원 전화번호
 * 4. 회원 비밀번호
 * 5. 회원 주소
 * 6. 회원 판매 상품
 * 7. 회원 닉네임
 * 8. 회원 역할
 * 9. 회원 상태
 * 10. 이메일 인증 여부
 * 11. 이메일 키
 * 12. 이메일 인증 날짜
 * 13. 비밀번호 초기화 키
 * 14. 비밀번호 초기화 가능 일자
 * 15. 생성일
 * 16. 업데이트일
 */