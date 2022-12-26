package com.example.shopproject.product.controller;

import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 일반 회원 상품관련 기능
 */

// 회원만 상품 상세정보 등록 가능
// 상세정보는 등록 & 삭제만 가능


@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/product")

public class ProductController {


    private final ProductService productService;

    // 회원 상품 리스트 검색(by 카테고리 id) API
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getProductListByCategory(@PathVariable Long id){

        return new ResponseEntity<>(
                productService.getProductListByCategory(id), HttpStatus.OK
        );
    }

    // 회원 상품 등록 API

    // 회원 상품 삭제 API

    // 회원 상품 수정 API


    // 회원 상품 상세정보 등록 API

    // 회원 상품 상세정보 삭제 API

}
