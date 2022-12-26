package com.example.shopproject.member.dto;

import com.example.shopproject.common.type.Role;
import com.example.shopproject.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


public class MemberSetRole {

    @Getter
    @Setter
    @Builder
    public static class Request {

        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @Valid
        private Role role;
    }

    @Getter
    @Setter
    @Builder
    public static class Response{


        private String email;
        private String name;
        private Role preRole;
        private Role postRole;

        public static Response fromEntity(MemberEntity entity, Role preRole){
            return Response.builder()
                           .email(entity.getEmail())
                           .name(entity.getName())
                           .preRole(preRole)
                           .postRole(entity.getRole())
                           .build();
        }
    }
}
