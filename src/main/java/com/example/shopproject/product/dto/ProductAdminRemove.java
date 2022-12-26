package com.example.shopproject.product.dto;

import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class ProductAdminRemove {


    @Getter
    @Setter
    @Builder
    public static class Request {


        @NotNull(message = "id는 필수 입력값입니다.")
        private Long id;

        private String productName;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {

        private String productName;

        private String message;

        public static Response fromEntity(ProductEntity productEntity) {

            return Response.builder()
                    .productName(productEntity.getProductName())
                    .message("상품이 정상적으로 삭제되었습니다.")
                    .build();
        }
    }
}
