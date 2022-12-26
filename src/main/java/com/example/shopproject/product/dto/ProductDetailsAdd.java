package com.example.shopproject.product.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDetailsAdd {


    @Getter
    @Setter
    @Builder
    public static class Request {


        @NotNull
        private Long productId;

        @NotBlank(message = "요약 글은 필수 입력값입니다.")
        private String summary;

        @NotBlank(message = "상세 설명은 필수 입력값입니다.")
        private String productDescription;

        private String maker;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {

        private static final String MESSAGE = " 의 상세정보 등록이 정상적으로 처리되었습니다.";

        private String message;

        public static Response of(String productName){

            return Response.builder()
                    .message(productName + MESSAGE)
                    .build();
        }
    }
}
