package com.example.shopproject.order.repository;

import com.example.shopproject.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
