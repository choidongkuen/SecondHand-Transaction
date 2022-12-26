package com.example.shopproject.product.entity;


import com.example.shopproject.member.entity.BasicTimeEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

/**
 * 1. 상품 엔티티(1:1)
 * 2. 상품 요약 설명
 * 3. 상품 상세 설명
 * 4. 상품 메이커ㅡ
 */

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_details")
@Entity

public class ProductDetailsEntity extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 양방향
    @OneToOne(mappedBy = "productDetailsEntity")
    private ProductEntity productEntity;

    private String summary;

    @Lob
    private String productDescription;

    private String maker;

}
