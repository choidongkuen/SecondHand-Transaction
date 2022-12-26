package com.example.shopproject.common.type;

import lombok.*;


@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EMAIL_ALREADY_EXIST("해당 이메일 회원이 이미 존재 합니다."),

    NICKNAME_ALREADY_EXISTS("해당 닉에임이 이미 존재 합니다."),

    USER_NOT_FOUND("해당 회원 정보가 존재하지 않습니다."),

    FAIL_SEND_MAIL("메일 전송에 실패했습니다."),

    MEMBER_NOT_EMAIL_AUTH("이메일 활성화 이후에 로그인을 해주세요."),

    PASSWORD_NOT_MATCH("입력하신 비밀번호에 오류가 있습니다."),

    RESETPASSWORDKEY_UNMATCH("패스워드 키가 존재하지 않습니다."),
    INVALID_DATE("유효하지 않은 날짜입니다."),

    RESET_PASSWORD_ERROR("비밀번호를 초 기화 하던 중에 오류가 발생했습니다."),

    MEMBER_STATUS_IS_STOP("해당 회원은 이용이 정지되었습니다."),

    ////////////////////////////////////////////////////////////////////

    CATEGORY_NOT_FOUND("해당 카테고리가 존재하지 않습니다."),



    ////////////////////////////////////////////////////////////////////


    PRODUCT_NOT_FOUND("해당 상품 정보가 존재하지 않습니다."),

    PRODUCT_ALREADY_SOLDOUT("해당 상품은 이미 판매 완료되었습니다."),
    PRODUCT_DETAILS_NOT_FOUNT("해당 상품 상세 정보가 존재하지 않습니다.");

    private final String message;


}
