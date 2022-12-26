package com.example.shopproject.admin.dto;

import com.example.shopproject.common.type.UserStatus;
import com.example.shopproject.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class MemberStatus {


    @Builder
    @Getter
    @Setter
    public static class Request {


        @NotEmpty(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;
        @Valid
        private UserStatus userStatus;
    }


    @Builder
    @Getter
    @Setter
    public static class Response {


        private String name;

        private UserStatus preUserStatus;

        private UserStatus postUserStatus;

        public static Response fromEntity(
                MemberEntity memberEntity, UserStatus preUserStatus) {

            return Response.builder()
                           .name(memberEntity.getName())
                           .preUserStatus(preUserStatus)
                           .postUserStatus(memberEntity.getUserStatus())
                           .build();
        }

    }

}
