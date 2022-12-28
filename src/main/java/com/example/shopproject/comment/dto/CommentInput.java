package com.example.shopproject.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CommentInput {


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Create {

        @NotBlank
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotNull
        private Long productId;

        @NotBlank
        @Length(max = 500)
        private String text;
    }


    @Getter
    @Setter
    @AllArgsConstructor
    public static class Update {

        @NotBlank
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotNull
        private Long commentId;


        @NotBlank
        @Length(max = 500)
        private String text;
    }
}
