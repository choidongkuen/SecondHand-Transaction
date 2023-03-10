package com.example.shopproject.product.repository;

import com.example.shopproject.product.entity.ProductDetailsEntity;
import com.example.shopproject.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    void deleteByProductDetailsEntity(ProductDetailsEntity productDetailsEntity);
}
