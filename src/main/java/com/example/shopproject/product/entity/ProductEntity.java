package com.example.shopproject.product.entity;


import com.example.shopproject.category.entity.CategoryEntity;
import com.example.shopproject.comment.entity.CommentEntity;
import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.member.entity.BasicTimeEntity;
import com.example.shopproject.member.entity.MemberEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1. 상품 아이디
 * 2. 상품 이름
 * 3. 상품 상세 정보
 * 4. 상품 판매 상태
 * 5. 상품 상태
 * 6. 상품 정가
 * 7. 상품 세일 여부
 * 8. 상품 세일 종료 일자
 * 9. 상품 세일 가격
 * 10. 상품 개수
 * 11. 상품 등록 일자
 * 12. 상품 수정 일자
 * 13. 상품 판매자
 */


/**
 * 상품(주인) 1 : 1 상품 상세 정보
 * 상품(주인) N : 1 판매자
 */
@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity

public class ProductEntity extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false)
    private String productName;


    // 등록 관점 N : 1 (양방향)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member")
    private MemberEntity memberEntity;

    // 1 : 1 (양방향)
    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "productDetails")
    private ProductDetailsEntity productDetailsEntity;

    // N : 1 (양방향)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category")
    private CategoryEntity categoryEntity;

    // 1 : N (양방향)
    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private ProductSaleStatus productSaleStatus;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    // 상품 정가
    @Column(nullable = false)
    private Long price;

    // 세일 가격(판매가)
    @Column(nullable = false)
    private Long salePrice;

    // (등록)상품 개수
    private Long stock;


}
