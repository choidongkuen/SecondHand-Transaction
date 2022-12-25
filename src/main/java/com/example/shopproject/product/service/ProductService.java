package com.example.shopproject.product.service;


import com.example.shopproject.product.dto.ProductAdd;



public interface ProductService {


    public ProductAdd.Response add(ProductAdd.Request request);

}
