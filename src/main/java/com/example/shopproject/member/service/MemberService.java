package com.example.shopproject.member.service;

import com.example.shopproject.member.dto.MemberAuth;
import com.example.shopproject.member.dto.MemberFindPassword.Request;
import com.example.shopproject.member.dto.MemberFindPassword.Response;
import com.example.shopproject.member.dto.MemberResetPassword;
import com.example.shopproject.member.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public interface MemberService extends UserDetailsService {


    MemberEntity signUp(MemberAuth.SignUp request);

    boolean emailAuth(String uuid);

    MemberEntity authentication(MemberAuth.SignIn request);


    Response findPassword(Request request);

    MemberResetPassword.Response resetPassword(MemberResetPassword.Request request);
}
