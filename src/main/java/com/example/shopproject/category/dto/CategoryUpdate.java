package com.example.shopproject.category.dto;


import com.example.shopproject.category.entity.CategoryEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class CategoryUpdate {

    @Getter
    @Setter
    @Builder
    public static class Request {

        @NotNull
        private Long id;
        @NotBlank(message = "카테고리명은 필수 입력값입니다.")
        private String categoryName;

        private int sortValue;

        private boolean usingYn;
    }

    @Getter
    @Setter
    @Builder
    public static class Response {

        private String categoryName;
        private String message;

        public static Response fromEntity(CategoryEntity entity) {

            return Response.builder()
                           .categoryName(entity.getCategoryName())
                           .message("카테고리가 정상적으로 업데이트되었습니다.")
                           .build();
        }

    }

}
