package com.example.shopproject.category.entity;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;


/**
 * 1. 카테고리 아이디
 * 2. 카테고리 이름
 * 3. 정렬 값
 * 4. 사용 가능 여부
 */

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="category")
@Entity

public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String categoryName;

    private int sortValue;

    private boolean usingYn;
}
