package com.example.shopproject.product.service;


import com.example.shopproject.product.dto.ProductAdminAdd.Response;
import com.example.shopproject.product.dto.ProductAdminRemove;
import com.example.shopproject.product.dto.ProductDto;
import com.example.shopproject.product.entity.ProductEntity;

import java.util.List;

import static com.example.shopproject.product.dto.ProductAdminAdd.Request;

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


    // 등록된 상품 리스트 검색 API
    List<ProductDto> getProductList();

    // 관리자 상품 등록 API
    Response adminAddProduct(Request request);


    ProductAdminRemove.Response adminRemoveProduct(ProductAdminRemove.Request request);

    ProductDto adminUpdateProduct(ProductDto productDto, Long id);

    List<ProductDto> getProductListByCategory(Long id);
}
