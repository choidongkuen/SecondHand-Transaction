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

    @GetMapping("/")
    public String main() {

        return "index";
    }


}
