package com.example.shopproject.member.controller;

import com.example.shopproject.common.security.TokenProvider;
import com.example.shopproject.member.dto.MemberAuth.SignIn;
import com.example.shopproject.member.dto.MemberFindPassword;
import com.example.shopproject.member.dto.MemberFindPassword.Response;
import com.example.shopproject.member.dto.MemberResetPassword.Request;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.example.shopproject.member.dto.MemberAuth.SignUp;


/**
 * Member 관련된 모든 요청 처리
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    private final TokenProvider tokenProvider;


    // 회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody @Valid SignUp request) {

        MemberEntity memberEntity = memberService.signUp(request);
        return new ResponseEntity<>(memberEntity, HttpStatus.OK);
    }


    // 이메일 인증
    @GetMapping("/email-auth")
    public ResponseEntity<?> emailAuth(@RequestParam String uuid) {

        boolean result = memberService.emailAuth(uuid);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignIn request
    ) {
        MemberEntity member = memberService.authentication(request);
        String token = this.tokenProvider.generateToken(member.getEmail(), member.getRole());
        log.info("user Login -> " + request.getEmail());
        return ResponseEntity.ok(token);
    }

    // 이메일을 통한 비밀번호 변경
    @PostMapping("/find/password")
    public ResponseEntity<?> findPassword(
            @RequestBody @Valid MemberFindPassword.Request request
    ) {
        Response result = memberService.findPassword(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    // 이메일을 통한 비밀번호 초기화
    @GetMapping("/reset/password")
    public ResponseEntity<?> resetPassword(
            @RequestBody @Valid Request request
    ){

        return new ResponseEntity<>(memberService.resetPassword(request),HttpStatus.OK);

    }
}
