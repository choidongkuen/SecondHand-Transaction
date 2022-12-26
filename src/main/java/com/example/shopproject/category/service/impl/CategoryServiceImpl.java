package com.example.shopproject.category.service.impl;

import com.example.shopproject.category.dto.CategoryDto;
import com.example.shopproject.category.entity.CategoryEntity;
import com.example.shopproject.category.exception.CategoryException;
import com.example.shopproject.category.repository.CategoryRepository;
import com.example.shopproject.category.service.CategoryService;
import com.example.shopproject.common.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.example.shopproject.category.dto.CategoryUpdate.Request;
import static com.example.shopproject.category.dto.CategoryUpdate.Response;

/**
 * 1. 카테고리 저장 API
 * 2. 카테고리 수정 API
 * 3. 카테고리 삭제 API
 * 4. 카테고리 목록 API
 */

@Slf4j
@RequiredArgsConstructor
@Service

public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    // 1. 카테고리 저장 API
    @Override
    public CategoryDto addCategory(String categoryName) {

        CategoryEntity categoryEntity = CategoryEntity.builder()
                                                      .categoryName(categoryName)
                                                      .usingYn(true)
                                                      .sortValue(0)
                                                      .build();

        return CategoryDto.fromEntity(
                categoryRepository.save(categoryEntity)
        );

    }

    // 2. 카테고리 수정 API
    @Override
    public Response updateCategory(Request request) {

        CategoryEntity categoryEntity = categoryRepository.findById(request.getId())
                                          .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryEntity.setCategoryName(request.getCategoryName());
        categoryEntity.setSortValue(request.getSortValue());
        categoryEntity.setUsingYn(request.isUsingYn());

        return Response.fromEntity(categoryRepository.save(categoryEntity));


    }

    // 3. 카테고리 삭제 API
    @Override
    public CategoryDto removeCategory(Long id) {

        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryException(ErrorCode.CATEGORY_NOT_FOUND));

        categoryRepository.delete(categoryEntity);

        return CategoryDto.fromEntity(categoryEntity);
    }

    // 4. 카테고리 목록 API
    @Override
    public List<CategoryDto> getCategoryList() {

        List<CategoryEntity> list
                = categoryRepository.findAll(getSortBySortValueDesc());

        return list.stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    public Sort getSortBySortValueDesc(){

        return Sort.by(Sort.Direction.DESC, "sortValue");
    }
}
