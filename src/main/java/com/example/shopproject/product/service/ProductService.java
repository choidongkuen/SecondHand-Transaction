package com.example.shopproject.product.service;


import com.example.shopproject.product.dto.*;

import java.util.List;

/**
 * 0. 등록된 상품 리스트 검색 API
 * 1. 관리자 상품 등록 API
 * 2. 판매자 상품 등록 API
 * 3. 관리자 상품 세일 설정 API
 * 4. 관리자 상품 상세 설명 등록 API
 * 5. 판매자 상품 상세 설명 등록 API
 * 6. 관리자 상품 상태 변경 API
 * 7. 판매자 상품 상태 변경 API
 * 8. 관리자 상품 삭제 API
 * 9. 판매자 상품 삭제 API
 */

public interface ProductService {


    // 관리자 상품 리스트 검색 API
    List<ProductDto> getProductList();

    // 상품 등록 API
    ProductAdd.Response addProduct(ProductAdd.Request request);

    // 상품 삭제 API
    ProductDto removeProduct(Long id);

    // 상품 수정 API
    ProductUpdate.Response updateProduct(ProductUpdate.Request request);

    // 회원 카테고리별 상품 리스트 검색 API
    List<ProductDto> getProductListByCategory(Long id);


    // 회원 상품 상세 정보 등록 API
    ProductDetailsAdd.Response addProductDetails(ProductDetailsAdd.Request request);

    // 회원 상품 상세 정보 삭제 API
    ProductDetailsDto removeProductDetails(Long id);

    // 회원 상품 상세 정보 조회 API
    ProductDetailsDto getProductDetailsList(Long id);
}
