package com.example.shopproject.product.entity;


import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.member.entity.BasicTimeEntity;
import com.example.shopproject.member.entity.MemberEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.time.LocalDate;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity

public class ProductEntity extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(nullable = false,unique = true)
    private String productName;

    @ManyToOne
    @JoinColumn(name="member")
    private MemberEntity memberEntity;

    @OneToOne
    @JoinColumn(name = "productDetails")
    private ProductDetails productDetails;


    @Enumerated(EnumType.STRING)
    private ProductSaleStatus productSaleStatus;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    // 상품 정가
    @Column(nullable = false)
    private Long price;

    // 세일 판매 여부자
    private boolean onSale;

    // 세일 종료 일자
    private LocalDate saleEndDt;

    // 세일 가격(판매가)
    private Long salePrice;

    private Long stock;

}
