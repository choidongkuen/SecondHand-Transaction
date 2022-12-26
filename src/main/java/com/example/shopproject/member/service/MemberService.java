package com.example.shopproject.member.service;


import com.example.shopproject.member.dto.*;
import com.example.shopproject.member.dto.MemberFindPassword.Request;
import com.example.shopproject.member.dto.MemberFindPassword.Response;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface MemberService{


    MemberDto signUp(MemberAuth.SignUp request);

    boolean emailAuth(String uuid);

    MemberDto authentication(MemberAuth.SignIn request);


    Response findPassword(Request request);

    MemberResetPassword.Response resetPassword(MemberResetPassword.Request request);

    List<MemberDto> getMemberList(Pageable pageable);

    UserDetails loadUserByUsername(String userName);

    MemberSetRole.Response setRole(MemberSetRole.Request request);

    MemberDto detail(String email);

    MemberStatus.Response setUserStatus(MemberStatus.Request request);

    MemberPassword.Response setUserPassword(MemberPassword.Request request);
}
