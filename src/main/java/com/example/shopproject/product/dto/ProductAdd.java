package com.example.shopproject.product.dto;

import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductAdd {


    @Getter
    @Setter
    @Builder
    public static class Request {

        @NotBlank(message = "이메일은 필수 입력값 입니다.")
        private String email;

        @NotNull(message = "상품 카테고리명은 필수 입력값 입니다.")
        private Long categoryId;

        @NotBlank(message = "상품 이름은 필수 입력값 입니다.")
        private String productName;

        @Valid
        private ProductStatus productStatus;

        @NotNull(message = "상품 정가 가격은 필수 입력값 입니다.")
        private Long price;

        @NotNull(message = "상품 판매 가격은 필수 입력값 입니다.")
        private Long salePrice;


        @NotNull(message = "상품 개수는 필수 입력값 입니다.")
        private Long stock;

    }

    @Getter
    @Setter
    @Builder
    public static class Response {

        private String email;

        private String productName;

        private String categoryName;

        public static ProductAdd.Response fromEntity(ProductEntity entity){
            return ProductAdd.Response.builder()
                  .email(entity.getMemberEntity().getEmail())
                  .productName(entity.getProductName())
                  .categoryName(entity.getCategoryEntity().getCategoryName())
                  .build();
        }
    }
}
