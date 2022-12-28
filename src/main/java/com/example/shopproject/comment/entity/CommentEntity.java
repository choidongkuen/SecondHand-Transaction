package com.example.shopproject.comment.entity;


import com.example.shopproject.member.entity.BasicTimeEntity;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="comment")
@Entity

public class CommentEntity extends BasicTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId", nullable = false)
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId" ,nullable = false)
    private ProductEntity productEntity;

    @Lob
    private String text;


}
