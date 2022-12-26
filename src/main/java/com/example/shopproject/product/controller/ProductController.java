package com.example.shopproject.product.controller;

import com.example.shopproject.product.dto.ProductAdd.Request;
import com.example.shopproject.product.dto.ProductDetailsAdd;
import com.example.shopproject.product.dto.ProductUpdate;
import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> getProductListByCategory(@PathVariable Long id) {

        return new ResponseEntity<>(
                productService.getProductListByCategory(id), HttpStatus.OK
        );
    }

    // 회원 상품 등록 API
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Request request) {

        return new ResponseEntity<>(
                productService.addProduct(request), HttpStatus.OK
        );
    }


    // 회원 상품 삭제 API
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> removeProduct(@PathVariable Long id) {

        return new ResponseEntity<>(
                productService.removeProduct(id), HttpStatus.OK
        );
    }


    // 회원 상품 수정 API
    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(
            @RequestBody @Valid ProductUpdate.Request request
    ) {

        return new ResponseEntity<>(
                productService.updateProduct(request), HttpStatus.OK
        );
    }

    /**
     * 상품 상세 정보
     * 상품 아이디 입력시 상세정보 조회
     * 등록 or 삭제
     */

    // 회원 상품 상세정보 조회 API
    @GetMapping("/detail/{id}/list")
    public ResponseEntity<?> getProductDetailsList(@PathVariable Long id){

        return new ResponseEntity<>(
                productService.getProductDetailsList(id), HttpStatus.OK
        );
    }

    // 회원 상품 상세정보 등록 API
    @PostMapping("/detail/add")
    public ResponseEntity<?> addProductDetails(
            @RequestBody @Valid ProductDetailsAdd.Request request
    ){

        return new ResponseEntity<>(
                productService.addProductDetails(request), HttpStatus.OK
        );
    }


    // 회원 상품 상세정보 삭제 API
    @DeleteMapping("/detail/remove/{id}")
    public ResponseEntity<?> removeProductDetails(@PathVariable Long id){

        return new ResponseEntity<>(
                productService.removeProductDetails(id), HttpStatus.OK
        );
    }
}
