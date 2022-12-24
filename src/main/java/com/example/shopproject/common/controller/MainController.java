package com.example.shopproject.common.controller;


import com.example.shopproject.common.mail.MailComponents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller

public class MainController {


    private final MailComponents mailComponents;

    // 메인 페이지(임의)
    @GetMapping("/")
    public String main() {

        return "index";
    }

    // 에러 페이지(임의 - 접근 관련)
    @GetMapping("/error/denied")
    public String error(){

        return "error/denied";
    }


}
