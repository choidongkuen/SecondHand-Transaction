package com.example.shopproject.product.dto;

import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ProductUserAdd {


    @Getter
    @Setter
    @Builder
    public static class Request {

        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        @Email(message = "이메일 형식으로 입력해주세요.")
        private String email;

        @NotBlank(message = "상품 이름은 필수 입력값 입니다.")
        private String productName;

        @NotBlank(message = "상품 가격은 필수 입력값 입니다.")
        private Long price;

        private ProductStatus productStatus;

        private Long categoryId;

        private Long stock;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {

        private String email;

        private String productName;

        private String categoryName;

        public static Response fromEntity(ProductEntity entity){
            return Response.builder()
                    .email(entity.getMemberEntity().getEmail())
                    .productName(entity.getProductName())
                    .categoryName(entity.getCategoryEntity().getCategoryName())
                    .build();
        }


    }
}
