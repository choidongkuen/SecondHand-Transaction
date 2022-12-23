package com.example.shopproject.member.dto;

import com.example.shopproject.member.entity.MemberEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberFindPassword {


    @Slf4j
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Request{


        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotBlank(message = "이름은 필수 입력값 입니다.")
        private String name;
    }


    @Slf4j
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public static class Response{

        private String email;
        private String uuid;

        public static Response fromEntity(MemberEntity entity){

            return Response.builder()
                    .email(entity.getEmail())
                    .uuid(entity.getResetPasswordKey())
                    .build();
        }
    }
}
