package com.example.shopproject.product.entity;


import com.example.shopproject.member.entity.BasicTimeEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 1. 상품 엔티티(1:1)
 * 2. 상품 요약 설명
 * 3. 상품 상세 설명
 * 4. 상품 메이커
 */

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
@Entity


public class ProductDetails extends BasicTimeEntity {

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

}
