package com.example.shopproject.comment.repository;

import com.example.shopproject.comment.dto.CommentDto;
import com.example.shopproject.comment.entity.CommentEntity;
import com.example.shopproject.product.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {


    List<CommentDto> findByProductEntity(ProductEntity productEntity);
}
