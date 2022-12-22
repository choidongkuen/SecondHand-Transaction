package com.example.shopproject.member.controller;

import com.example.shopproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.shopproject.member.dto.MemberAuth.SignUp;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUp request){

        return ResponseEntity.ok(memberService.signUp(request));
    }


    // 로그인
    @GetMapping("/signin")
    public String signIn(){
        return "member/SignIn";
    }

    // 로그인 실패
    @GetMapping("/signin/fail")
    public String memberLonginFail(Model model){

        model.addAttribute("loginFailMsg","아이디 또는 비밀번호를 확인해주세요.");
        return "member/SignIn";
    }
}
