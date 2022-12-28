package com.example.shopproject.comment.service.impl;

import com.example.shopproject.comment.dto.CommentDto;
import com.example.shopproject.comment.dto.CommentInput;
import com.example.shopproject.comment.entity.CommentEntity;
import com.example.shopproject.comment.exception.CommentException;
import com.example.shopproject.comment.repository.CommentRepository;
import com.example.shopproject.comment.service.CommentService;
import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.exception.MemberException;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.product.entity.ProductEntity;
import com.example.shopproject.product.exception.ProductException;
import com.example.shopproject.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor

public class CommentServiceImpl implements CommentService {

    private final ProductRepository productRepository;

    private final MemberRepository memberRepository;

    private final CommentRepository commentRepository;



    // 댓글 추가 API
    @Override
    public CommentDto addComment(CommentInput.Create request) {

        MemberEntity memberEntity = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        ProductEntity productEntity = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));


        return CommentDto.fromEntity(commentRepository.save(CommentEntity.builder()
                .memberEntity(memberEntity)
                .productEntity(productEntity)
                .text(request.getText())
                .build()
        ));
    }


    // 댓글 수정 API
    @Override
    public CommentDto updateComment(CommentInput.Update request) {

        MemberEntity memberEntity = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        CommentEntity commentEntity = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));

        if(!request.getEmail().equals(commentEntity.getMemberEntity().getEmail())) {
            throw new MemberException(ErrorCode.USER_NOT_FOUND);
        }

        commentEntity.setText(request.getText());

        return CommentDto.fromEntity(commentRepository.save(commentEntity));
    }

    // 댓글 삭제 API
    @Override
    public CommentDto removeComment(Long id) {


        CommentEntity commentEntity = commentRepository.findById(id)
                .orElseThrow(() -> new CommentException(ErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(commentEntity);

        return CommentDto.fromEntity(commentEntity);
    }
}
