package com.example.shopproject.order.dto;

import com.example.shopproject.order.entity.OrderEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class OrderDto {

    private String orderUser;

    private String productName;

    private LocalDateTime orderDt;


    public static OrderDto fromEntity(OrderEntity orderEntity){

        return OrderDto.builder()
                .orderUser(orderEntity.getMemberEntity().getName())
                .productName(orderEntity.getProductEntity().getProductName())
                .orderDt(LocalDateTime.now())
                .build();
    }
}
