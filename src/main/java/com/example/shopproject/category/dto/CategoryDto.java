package com.example.shopproject.category.dto;


import com.example.shopproject.category.entity.CategoryEntity;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String categoryName;

    private boolean usingYn;

    private String message;


    public static CategoryDto fromEntity(CategoryEntity entity){

        return CategoryDto.builder()
                .categoryName(entity.getCategoryName())
                .usingYn(entity.isUsingYn())
                .message("카테고리가 정상적으로 처리되었습니다.")
                .build();
    }
}
