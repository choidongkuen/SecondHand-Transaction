package com.example.shopproject.main.Mail;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Mail {

    private String mail;
    private String subject;
    private String text;
}
