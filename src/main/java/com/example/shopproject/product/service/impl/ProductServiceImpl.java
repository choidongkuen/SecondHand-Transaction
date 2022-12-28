package com.example.shopproject.product.service.impl;

import com.example.shopproject.category.entity.CategoryEntity;
import com.example.shopproject.category.exception.CategoryException;
import com.example.shopproject.category.repository.CategoryRepository;
import com.example.shopproject.comment.dto.CommentDto;
import com.example.shopproject.common.constants.CacheKey;
import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.common.type.UserStatus;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.exception.MemberException;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.product.dto.*;
import com.example.shopproject.product.entity.ProductDetailsEntity;
import com.example.shopproject.product.entity.ProductEntity;
import com.example.shopproject.product.exception.ProductException;
import com.example.shopproject.product.repository.ProductDetailsRepostitory;
import com.example.shopproject.product.repository.ProductRepository;
import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.shopproject.common.type.ProductSaleStatus.ON_SALE;
import static com.example.shopproject.product.dto.ProductDto.fromEntity;



@Slf4j
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    private final MemberRepository memberRepository;

    private final ProductDetailsRepostitory productDetailsRepostitory;


    // 상품 리스트 검색 API
    @Override
    public List<ProductDto> getProductList() {

        return productRepository.findAll().stream()
                                .map(ProductDto::fromEntity)
                                .collect(Collectors.toList());
    }


    // 회원 카테고리별 상품 리스트 검색 API
    @Transactional
    @Override
    public List<ProductDto> getProductListByCategory(Long id) {

        if (categoryRepository.countById(id) == 0) {
            throw new CategoryException(ErrorCode.CATEGORY_NOT_FOUND);
        }

        return productRepository.findAll(Sort.by(Sort.Order.desc("createdAt"))).stream()
                                .filter(p -> p.getCategoryEntity().getId().equals(id))
                                .map(ProductDto::fromEntity)
                                .collect(Collectors.toList());
    }


    // 상품 등록 API
    @Transactional
    @Override
    public ProductAdd.Response addProduct(ProductAdd.Request request) {

        MemberEntity memberEntity = memberRepository.findByEmail(request.getEmail())
                                                    .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        // 정지된 사용자인 경우
        if (memberEntity.getUserStatus().equals(UserStatus.STOP))
            throw new MemberException(ErrorCode.MEMBER_STATUS_IS_STOP);

        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId())
                                                          .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));
        return ProductAdd.Response.fromEntity(
                productRepository.save(ProductEntity.builder()
                        .productName(request.getProductName())
                        .memberEntity(memberEntity)
                        .categoryEntity(categoryEntity)
                        .productSaleStatus(ON_SALE)
                        .productStatus(request.getProductStatus())
                        .price(request.getPrice())
                        .salePrice(request.getSalePrice())
                        .stock(request.getStock())
                        .build()
                )
        );
    }

    // 상품 삭제 API
    @Transactional
    @Override
    public ProductDto removeProduct(Long id) {

        ProductEntity productEntity = productRepository.findById(id)
                                                       .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        ProductDetailsEntity detailsEntity = productEntity.getProductDetailsEntity();

        if(detailsEntity != null){
            productDetailsRepostitory.delete(detailsEntity);
        }

        productRepository.delete(productEntity);
        return fromEntity(productEntity);
    }

    // 상품 수정 API
    @Transactional
    @Override
    public ProductUpdate.Response updateProduct(ProductUpdate.Request request) {


        // 변경하고자 하는 상품
        ProductEntity productEntity = productRepository.findById(request.getProductId())
                                                       .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        // 변경하고자 하는 카테고리
        CategoryEntity categoryEntity = categoryRepository.findById(request.getCategoryId())
                                                          .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));

        productEntity.setCategoryEntity(categoryEntity);
        productEntity.setProductName(request.getProductName());
        productEntity.setSalePrice(request.getSalePrice());
        productEntity.setProductSaleStatus(request.getProductSaleStatus());
        productEntity.setProductStatus(request.getProductStatus());
        productEntity.setStock(request.getStock());

        return ProductUpdate.Response.fromEntity(
                productRepository.save(productEntity));
    }


    // 회원 상품 상세 정보 등록 API
    @Transactional
    @Override
    public ProductDetailsAdd.Response addProductDetails(ProductDetailsAdd.Request request) {

        ProductEntity productEntity = productRepository.findById(request.getProductId())
                                                       .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        ProductDetailsEntity productDetailsEntity = ProductDetailsEntity.builder()
                                            .summary(request.getSummary())
                                            .productDescription(request.getProductDescription())
                                            .maker(request.getMaker())
                                            .build();

        productEntity.setProductDetailsEntity(productDetailsRepostitory.save(productDetailsEntity));
        productRepository.save(productEntity);

        return ProductDetailsAdd.Response.of(productEntity.getProductName());
    }

    // 회원 상품 상세 정보 삭제 API

    @Transactional
    @Override
    public ProductDetailsDto removeProductDetails(Long id) {

        ProductDetailsEntity productDetailsEntity = productDetailsRepostitory.findById(id)
                                     .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_DETAILS_NOT_FOUNT));

        productRepository.deleteByProductDetailsEntity(productDetailsEntity);

        productDetailsRepostitory.delete(productDetailsEntity);

        return ProductDetailsDto.fromEntity(productDetailsEntity);

    }

    // 회원 상품 상세 정보 조회 API
    @Transactional
    @Override
    public ProductDetailsDto getProductDetails(Long productId) {

        ProductEntity productEntity = productRepository.findById(productId)
                                                       .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        if (productEntity.getProductDetailsEntity() == null) {
            throw new ProductException(ErrorCode.PRODUCT_DETAILS_NOT_FOUNT);
        }

        return ProductDetailsDto.fromEntity(productEntity.getProductDetailsEntity());
    }



    // 상품 comment 조회 API
    @Override
    @Cacheable(key = "#id", value = CacheKey.CACHE_KEY)
    public List<CommentDto> getComments(Long id) {

        ProductEntity productEntity = productRepository.findById(id)
                       .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        return productEntity.getComments().stream()
                .map(CommentDto::fromEntity)
                .collect(Collectors.toList());
    }
}
