package com.example.shopproject.product.service.impl;

import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.product.dto.ProductAdminRemove;
import com.example.shopproject.product.dto.ProductDto;
import com.example.shopproject.product.entity.ProductEntity;
import com.example.shopproject.product.exception.ProductException;
import com.example.shopproject.product.repository.ProductRepository;
import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.shopproject.common.type.ProductSaleStatus.ON_SALE;
import static com.example.shopproject.product.dto.ProductAdminAdd.Request;
import static com.example.shopproject.product.dto.ProductAdminAdd.Response;


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

@Slf4j
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    @Override
    @Transactional
    public List<ProductDto> getProductList() {

        return productRepository.findAll().stream()
                                .map(ProductDto::fromEntity)
                                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Response adminAddProduct(Request request) {

        ProductEntity productEntity = ProductEntity.builder()
                                                   .productName(request.getProductName())
                                                   .price(request.getPrice())
                                                   .salePrice(request.getSalePrice())
                                                   .stock(request.getStock())
                                                   .productStatus(request.getProductStatus())
                                                   .productSaleStatus(ON_SALE)
                                                   .build();

        productRepository.save(productEntity);

        return Response.fromEntity(
                productRepository.save(productEntity)
        );
    }

    @Override
    public ProductAdminRemove.Response adminRemoveProduct(ProductAdminRemove.Request request) {

        ProductEntity productEntity = productRepository.findById(request.getId())
                           .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        productRepository.delete(productEntity);

        return ProductAdminRemove.Response.fromEntity(productEntity);


    }
}
