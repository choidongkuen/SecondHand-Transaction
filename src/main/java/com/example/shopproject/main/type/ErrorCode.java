package com.example.shopproject.main.type;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Getter
public enum ErrorCode {

    EMAIL_ALREADY_EXIST("해당 이메일 회원이 이미 존재 합니다."),

    NICKNAME_ALREADY_EXISTS("해당 닉에임이 이미 존재 합니다."),

    USER_NOT_FOUND("해당 회원이 존재하지 않습니다."),


    FAIL_SEND_MAIL("메일 전송에 실패했습니다.");



    private final String message;


}
