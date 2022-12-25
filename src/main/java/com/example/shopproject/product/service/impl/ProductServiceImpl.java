package com.example.shopproject.product.service.impl;

import com.example.shopproject.product.dto.ProductAdd;
import com.example.shopproject.product.repository.ProductRepository;
import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;


    public ProductAdd.Response add(ProductAdd.Request request){

        return null;



    }



}
