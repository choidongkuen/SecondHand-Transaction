package com.example.shopproject.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MemberAuth {


    @Getter
    @Setter
    @Builder
    public static class SignUp{

        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotBlank(message = "이름은 필수 입력값 입니다.")
        private String name;

        @NotBlank(message = "핸드폰 번호는 필수 입력값 입니다.")
        private String phone;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
        private String password;

        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자로 입력해주세요.")
        private String checkPassword;

    }


    @Getter
    @Setter
    @Builder
    public static class SignIn{


        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;


        @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
        @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
        private String password;

    }
}
