package com.example.shopproject.comment.controller;


import com.example.shopproject.comment.dto.CommentInput;
import com.example.shopproject.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/add")
    public ResponseEntity<?> addComment(
            @RequestBody @Valid CommentInput.Create request) {


        return new ResponseEntity<>(
                commentService.addComment(request), HttpStatus.OK
        );

    }

    @PutMapping("/comment/update")
    public ResponseEntity<?> updateComment(
            @RequestBody @Valid CommentInput.Update request) {

        return new ResponseEntity<>(
                commentService.updateComment(request), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeComment(@PathVariable Long id) {

        return new ResponseEntity<>(
                commentService.removeComment(id), HttpStatus.OK
        );
    }
}
