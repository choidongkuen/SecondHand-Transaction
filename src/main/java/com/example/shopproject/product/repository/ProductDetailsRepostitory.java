package com.example.shopproject.product.repository;

import com.example.shopproject.product.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductDetailsRepostitory extends JpaRepository<ProductDetails, Long> {
}
