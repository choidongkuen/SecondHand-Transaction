package com.example.shopproject.product.dto;

import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 관리자가 상품 등록
 * 판매자가 올린 판매가에 할인 적용 가능
 * 요청 : 상품 이름, 상품 정가, 상품 판매가, 상품 상태, 상품 개수
 * 응답 : 상품 이름
 */
public class ProductAdminAdd {


    @Getter
    @Setter
    @Builder
    public static class Request{


        @NotBlank(message = "상품 이름은 필수 입력값 입니다.")
        private String productName;

        @NotNull(message = "상품 정가 가격은 필수 입력값 입니다.")
        private Long price;

        @NotNull(message = "상품 판매 가격은 필수 입력값 입니다.")
        private Long salePrice;

        @Valid
        private ProductStatus productStatus;

        @NotNull(message = "상품 개수는 필수 입력값 입니다.")
        private Long stock;

    }

    @Getter
    @Setter
    @Builder
    public static class Response{

        private String productName;
        private ProductSaleStatus productSaleStatus;
        private String message;

        public static Response fromEntity(ProductEntity entity) {

            return Response.builder()
                    .productName(entity.getProductName())
                    .productSaleStatus(entity.getProductSaleStatus())
                    .message("상품이 정상적으로 등록되었습니다.")
                    .build();
        }
    }
}
