package com.example.shopproject.comment.service;

import com.example.shopproject.comment.dto.CommentDto;
import com.example.shopproject.comment.dto.CommentInput;

public interface CommentService {
    CommentDto addComment(CommentInput.Create request);

    CommentDto updateComment(CommentInput.Update request);

    CommentDto removeComment(Long id);
}
