package com.example.shopproject.category.service;

import com.example.shopproject.category.dto.CategoryDto;
import com.example.shopproject.category.dto.CategoryUpdate;

import java.util.List;

/**
 * 1. 카테고리 저장 API
 * 2. 카테고리 수정 API
 * 3. 카테고리 삭제 API
 * 4. 카테고리 목록 API
 */

public interface CategoryService {


    CategoryDto addCategory(String categoryName);

    CategoryUpdate.Response updateCategory(CategoryUpdate.Request request);

    CategoryDto removeCategory(Long id);

    List<CategoryDto> getCategoryList();
}
