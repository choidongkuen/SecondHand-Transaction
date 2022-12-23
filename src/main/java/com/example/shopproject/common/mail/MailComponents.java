package com.example.shopproject.common.mail;


import com.example.shopproject.common.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;

@Component
@RequiredArgsConstructor
public class MailComponents {


    private final JavaMailSender javaMailSender;


    public void sendMailTest(){


        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("danaver12@daum.net");
        message.setSubject("안녕하세요 제로베이스 입니다.");
        message.setText("안녕하세요. 제로베이스 입니다.");


        javaMailSender.send(message);

    }

    public boolean sendMail(Mail mail){

        boolean result = false;


        MimeMessagePreparator msg = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {


                MimeMessageHelper mimeMessageHelper =
                        new MimeMessageHelper(mimeMessage, true, "UTF-8");

                mimeMessageHelper.setTo(mail.getEmail());
                mimeMessageHelper.setSubject(mail.getSubject());
                mimeMessageHelper.setText(mail.getText(),true);
            }
        };



        try{
            javaMailSender.send(msg);
            result = true;
        }catch (Exception e){
            System.out.println(ErrorCode.FAIL_SEND_MAIL.getMessage());
        }


        return result;
    }


}
