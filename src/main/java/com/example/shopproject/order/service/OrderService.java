package com.example.shopproject.order.service;

import com.example.shopproject.order.dto.OrderDto;

import java.util.List;

public interface OrderService {

    OrderDto order(Long productId, String email);

    OrderDto orderCancel(Long orderId);

    List<OrderDto> orderList(Long id);
}
