package com.example.shopproject.member.service;

import com.example.shopproject.member.dto.MemberAuth;
import com.example.shopproject.member.entity.MemberEntity;

public interface MemberService {


    public MemberEntity signUp(MemberAuth.SignUp request);

}
