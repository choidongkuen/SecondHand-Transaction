package com.example.shopproject.admin.dto;

import com.example.shopproject.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class MemberPassword {

    @Getter
    @Setter
    @Builder
    public static class Request{

        @NotEmpty(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
        private String password;

    }

    @Getter
    @Setter
    @Builder
    public static class Response{

        private String email;
        private String prePassword;
        private String postPassword;

        public static Response fromEntity(MemberEntity entity, String prePassword){

            return Response.builder()
                    .email(entity.getEmail())
                    .prePassword(prePassword)
                    .postPassword(entity.getPassword())
                    .build();
        }
    }
}
