package com.example.shopproject.common.mail;



import com.example.shopproject.member.dto.MemberAuth;
import com.example.shopproject.member.dto.MemberFindPassword;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Getter
@Setter

public class Mail {

    private static final String SUBJECT = "[제로 쇼핑몰 알림입니다.🚀]";

    private String subject;
    private String text;
    private String email;

    public Mail(Object object, String uuid){

        this.subject = SUBJECT;

        if(object instanceof MemberAuth.SignUp){

            this.email =((MemberAuth.SignUp) object).getEmail();
            this.text = "<p> 제로 쇼핑몰 회원 가입을 축하드립니다. <p><p> 아래 링크를 클릭하셔서 가입을 완료 해주세요.</p>"
                    + "<div><a href = 'http://localhost:8080/member/email-auth?uuid=" + uuid + "'> 가입완료 </a></div>";
        }
        if(object instanceof MemberFindPassword.Request){

            this.email = ((MemberFindPassword.Request) object).getEmail();
            this.text = " <p> 제로 쇼핑몰 회원의 비밀번호를 초기화 합니다.<p><p> 아래 링크를 클릭하셔서 비밀번호 초기화를 완료 해주세요. </p>"
                    + " <div><a href = 'http://localhost:8080/member/find/password=" + uuid + "'> 비밀번호 초기화 </a></div>";


        }
    }
}
