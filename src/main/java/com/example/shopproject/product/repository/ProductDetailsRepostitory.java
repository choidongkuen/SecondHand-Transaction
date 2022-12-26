package com.example.shopproject.product.repository;

import com.example.shopproject.product.entity.ProductDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailsRepostitory extends JpaRepository<ProductDetailsEntity, Long> {
}
