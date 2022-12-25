package com.example.shopproject.product.entity;


import com.example.shopproject.common.type.ProductSeason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity


/**
 * 1. 상품 엔티티(1:1)
 * 2. 상품 요약 설명
 * 3. 상품 상세 설명
 * 4. 상품 메이커
 * 5. 상품 시즌
 */
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne(mappedBy = "productDetails")
    private ProductEntity productEntity;

    private String summary;

    @Lob
    private String productDescription;

    private String maker;

    private ProductSeason productSeason;


}
