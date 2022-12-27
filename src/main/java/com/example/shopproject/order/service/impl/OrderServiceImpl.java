package com.example.shopproject.order.service.impl;

import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.common.type.ProductSaleStatus;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.exception.MemberException;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.order.dto.OrderDto;
import com.example.shopproject.order.entity.OrderEntity;
import com.example.shopproject.order.exception.OrderException;
import com.example.shopproject.order.repository.OrderRepository;
import com.example.shopproject.order.service.OrderService;
import com.example.shopproject.product.entity.ProductEntity;
import com.example.shopproject.product.exception.ProductException;
import com.example.shopproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.shopproject.common.type.OrderStatus.ORDER;
import static com.example.shopproject.common.type.ProductSaleStatus.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;


    // 사용자 주문 API
    @Transactional
    @Override
    public OrderDto order(Long productId, String email) {

        // 구매할 물건
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        // 구매자
        MemberEntity memberEntity = memberRepository.findByEmail(email)
             .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        orderPreparation(productEntity);

        return OrderDto.fromEntity(orderRepository.save(OrderEntity.builder()
                .orderStatus(ORDER)
                .memberEntity(memberEntity)
                .productEntity(productEntity)
                .orderDt(LocalDateTime.now())
                .build())
        );
    }

    private void orderPreparation(ProductEntity productEntity) {

        if(productEntity.getStock() < 1){
            throw new OrderException(ErrorCode.STOCK_UNSATISFIED);
        }

        if(productEntity.getStock() == 1){
            productEntity.setProductSaleStatus(SOLD_OUT);
        }

        productEntity.setStock(productEntity.getStock() - 1);
        productRepository.save(productEntity);
    }


    // 사용자 주문 취소 API
    @Transactional
    @Override
    public OrderDto orderCancel(Long orderId) {

        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ErrorCode.ORDER_NOT_FOUND));

        ProductEntity productEntity = orderEntity.getProductEntity();

        if (productEntity.getProductSaleStatus().equals(SOLD_OUT)){

            productEntity.setProductSaleStatus(ON_SALE);
        }
        productEntity.setStock(productEntity.getStock() + 1);

        productRepository.save(productEntity);

        orderRepository.delete(orderEntity);

        return OrderDto.fromEntity(orderEntity);
    }


    // 사용자 주문 내역 조회 API
    @Transactional
    @Override
    public List<OrderDto> orderList(Long userId) {


        MemberEntity memberEntity = memberRepository.findById(userId)
                .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        return memberEntity.getOrders().stream()
                .map(OrderDto::fromEntity)
                .collect(Collectors.toList());

    }
}
