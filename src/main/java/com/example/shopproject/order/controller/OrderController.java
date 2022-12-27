package com.example.shopproject.order.controller;


import com.example.shopproject.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


@Slf4j
@RequiredArgsConstructor
@RestController

public class OrderController {


    private final OrderService orderService;

    // (단일)상품 주문 API
    @PostMapping("/order/{productId}")
    public ResponseEntity<?> order(
            @RequestParam String userEmail, @PathVariable Long productId) {

        return new ResponseEntity<>(
                orderService.order(productId, userEmail), HttpStatus.OK
        );

    }

    // (단일)상품 주문 취소 API
    @DeleteMapping("/order/{id}")
    public ResponseEntity<?> orderCancel(@PathVariable Long id) {

        return new ResponseEntity<>(
                orderService.orderCancel(id), HttpStatus.OK
        );
    }

    // 회원 주문 조회 API
    @GetMapping("/order/{id}/list")
    public ResponseEntity<?> orderList(@PathVariable Long id){

        return new ResponseEntity<>(
                orderService.orderList(id), HttpStatus.OK
        );
    }
}
