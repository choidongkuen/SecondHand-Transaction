package com.example.shopproject.order.entity;


import com.example.shopproject.common.type.OrderStatus;
import com.example.shopproject.member.entity.BasicTimeEntity;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.order.service.OrderService;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@Entity
@DynamicInsert

public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity; // 주문자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity; // 주문한 상품


    private LocalDateTime orderDt; // 주문 일시


}
