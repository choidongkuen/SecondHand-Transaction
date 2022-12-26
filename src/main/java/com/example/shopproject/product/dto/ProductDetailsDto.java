package com.example.shopproject.product.dto;


import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductDetailsEntity;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsDto {

    private static final String MESSAGE = "조회 서비스가 정상적으로 처리되었습니다.";

    private String productName;

    private Long salePrice;

    private Long price;

    private ProductSaleStatus saleStatus;

    private ProductStatus status;

    private String summary;

    private String productDescription;

    private String maker;

    private String message;


    public static ProductDetailsDto fromEntity(ProductDetailsEntity entity){

        ProductEntity productEntity = entity.getProductEntity();

        return ProductDetailsDto.builder()
                .productName(productEntity.getProductName())
                .salePrice(productEntity.getSalePrice())
                .price(productEntity.getPrice())
                .saleStatus(productEntity.getProductSaleStatus())
                .status(productEntity.getProductStatus())
                .summary(entity.getSummary())
                .productDescription(entity.getProductDescription())
                .maker(entity.getMaker())
                .message(MESSAGE)
                .build();
    }


}
