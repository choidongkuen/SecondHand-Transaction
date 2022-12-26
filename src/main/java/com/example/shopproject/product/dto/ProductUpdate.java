package com.example.shopproject.product.dto;

import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 1. 카테고리 id
 * 2. 제품 이름
 * 3. 제품 판매 가격
 * 4. 제품 판매 상태
 * 5. 제품 상태
 * 6. 제품 수량
 */
public class ProductUpdate {


    @Getter
    @Setter
    @Builder
    public static class Request {


        private Long productId;

        private Long categoryId;

        private String productName;

        private String price;

        private Long salePrice;

        private ProductSaleStatus productSaleStatus;

        private ProductStatus productStatus;

        private Long stock;

    }


    @Getter
    @Setter
    @Builder

    public static class Response {

        private static final String MESSAGE = "상품 수정이 정상적으로 처리되었습니다.";

        private String productName;

        private String message;

        public static Response fromEntity(ProductEntity entity) {

            return Response.builder()
               .productName(entity.getProductName())
               .message(MESSAGE)
               .build();
        }
    }
}
