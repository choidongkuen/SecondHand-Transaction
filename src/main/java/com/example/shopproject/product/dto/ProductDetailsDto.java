package com.example.shopproject.product.dto;


import com.example.shopproject.product.entity.ProductDetailsEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {

    private static final String MESSAGE = " 정상적으로 처리되었습니다.";

    private String productName;

    private String message;


    public static ProductDetailsDto fromEntity(ProductDetailsEntity entity){

        return ProductDetailsDto.builder()
                .productName(entity.getProductEntity().getProductName())
                .message(MESSAGE)
                .build();
    }


}
