package com.example.shopproject.comment.dto;


import com.example.shopproject.comment.entity.CommentEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor


public class CommentDto {


    private Long commentId;

    private String memberEmail;

    private String productName;


    public static CommentDto fromEntity(CommentEntity commentEntity) {

        return CommentDto.builder()
                         .commentId(commentEntity.getId())
                         .memberEmail(commentEntity.getMemberEntity().getEmail())
                         .productName(commentEntity.getProductEntity().getProductName())
                         .build();
    }
}
