package com.example.shopproject.member.dto;

import com.example.shopproject.member.entity.MemberEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class MemberResetPassword {


    @Slf4j
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request{

        @NotBlank
        private String uuid;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
        private String password;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
        private String checkPassword;
    }


    @Slf4j
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response{

        private String modifiedPassword;
        private LocalDateTime modifiedDt;

        public static Response fromEntity(MemberEntity entity){

            return Response.builder()
                    .modifiedPassword(entity.getPassword())
                    .modifiedDt(LocalDateTime.now())
                    .build();
        }
    }
}
