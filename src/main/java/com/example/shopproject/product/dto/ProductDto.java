package com.example.shopproject.product.dto;


import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ProductDto {

    private String productName;

    private ProductSaleStatus productSaleStatus;

    private ProductStatus productStatus;

    private Long price;

    private Long salePrice;

    private Long stock;

    public static ProductDto fromEntity(ProductEntity entity) {

        return ProductDto.builder()
                 .productName(entity.getProductName())
                 .productSaleStatus(entity.getProductSaleStatus())
                 .productStatus(entity.getProductStatus())
                 .salePrice(entity.getSalePrice())
                 .stock(entity.getStock())
                 .build();
    }

}
