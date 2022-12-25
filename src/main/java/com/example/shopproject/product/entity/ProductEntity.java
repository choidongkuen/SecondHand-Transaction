package com.example.shopproject.product.entity;


import com.example.shopproject.common.type.ProductStatus;
import com.example.shopproject.member.entity.BasicEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="product")
@Entity

public class ProductEntity extends BasicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne
    private ProductDetails productDetails;

    @Column(nullable = false,unique = true)
    private String productName;

    @Column(nullable = false)
    private Long price;

    private boolean onSale;

    private LocalDate saleEndDt;

    private Long salePrice;

    @Column(nullable = false)
    private Long stock;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @CreatedDate
    @Column(updatable = false)
    LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false)
    LocalDateTime updatedAt;



}
