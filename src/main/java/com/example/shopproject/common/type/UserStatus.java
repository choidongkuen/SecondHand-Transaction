package com.example.shopproject.common.type;


/**
 * USING : 정상적인 사용자 (이메일 인증 완료)
 * STOP : 이용 불가
 * REQ : 이메일 인증 필요 (이메일 인증 전)
 */
public enum UserStatus {

    USING("사용중인 회원입니다."),
    STOP("정지상태의 회원입니다."),
    REQ("이메일 인증이 필요한 회원입니다.");

    private String statusMsg;

    private UserStatus(String message) {
        this.statusMsg = message;
    }
}
