package com.example.shopproject.member;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Memberdto {


    @NotBlank(message = "이메일은 필수 입력값 입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "이름은 필수 입력값 입니다.")
    private String name;


    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Length(min = 8, max = 16, message = "비밀번호는 8 ~ 16 자 로 입력해주세요.")
    private String password;



    @NotBlank(message = "주소는 필수 입력값 입니다.")
    private String address;


}
