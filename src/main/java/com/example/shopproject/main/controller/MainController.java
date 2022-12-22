package com.example.shopproject.main.controller;


import com.example.shopproject.main.Mail.Mail;
import com.example.shopproject.main.Mail.MailComponents;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@Controller

public class MainController {


    private final MailComponents mailComponents;

    @GetMapping("/")
    public String main() {

        Mail mail = Mail.builder()
                        .mail("danaver12@daum.net")
                        .subject(" 안녕하세요 제로베이스 입니다.")
                        .text("<p> 안녕하세요.</p><p>반갑습니다.</p>")
                        .build();

        mailComponents.sendMail(mail);

        return "index";
    }


}
