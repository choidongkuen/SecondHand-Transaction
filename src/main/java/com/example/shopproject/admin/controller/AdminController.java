package com.example.shopproject.admin.controller;


import com.example.shopproject.admin.dto.MemberPassword;
import com.example.shopproject.admin.dto.MemberSetRole;
import com.example.shopproject.admin.dto.MemberStatus;
import com.example.shopproject.member.service.MemberService;
import com.example.shopproject.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;

import static com.example.shopproject.product.dto.ProductAdminAdd.Request;


/**
 * 1. 회원 관련 관리자 기능
 */

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {


    private final MemberService memberService;
    private final ProductService productService;


    // 관리자 회원 리스트 검색 API
    @GetMapping("/member/list")
    public ResponseEntity<?> getMemberList(
            @PageableDefault(size = 10, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable
    ) {

        return new ResponseEntity<>(
                memberService.getMemberList(pageable), HttpStatus.OK
        );
    }

    // 각 회원 상세 정보 검색 API
    @GetMapping("/member/detail")
    public ResponseEntity<?> getMemberDetail(
            @RequestParam @Email(message = "이메일은 필수 입력값 입니다.") String email) {

        return new ResponseEntity<>(
                memberService.detail(email), HttpStatus.OK
        );
    }


    // 회원 역할 변경 API
    @PostMapping("/member/role")
    public ResponseEntity<?> changeRole(
            @RequestBody @Valid MemberSetRole.Request request) {

        return new ResponseEntity<>(
                memberService.setRole(request), HttpStatus.OK
        );
    }

    // 회원 상태 변경 API
    @PostMapping("/member/status")
    public ResponseEntity<?> changeStatus(
            @RequestBody @Valid MemberStatus.Request request) {


        return new ResponseEntity<>(
                memberService.setUserStatus(request), HttpStatus.OK
        );


    }

    // 회원 비밀번호 변경 API
    @PostMapping("/member/password")
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid MemberPassword.Request request) {

        return new ResponseEntity<>(
                memberService.setUserPassword(request), HttpStatus.OK
        );
    }


    /**
     * 상품 관련 관리자 기능
     */


    // 관리자 상품 리스트 검색 API
    @GetMapping("/product/list")
    public ResponseEntity<?> getProductList(
            @PageableDefault(size = 10, sort = "createdAt",
                    direction = Sort.Direction.DESC) Pageable pageable) {

        return new ResponseEntity<>(
                productService.getProductList(), HttpStatus.OK
        );


    }

    // 관리자 상품 등록 API
    @PostMapping("/product/add")
    public ResponseEntity<?> addProduct(@RequestBody @Valid Request request) {

        return new ResponseEntity<>(
                productService.adminAddProduct(request), HttpStatus.OK
        );
    }

}
