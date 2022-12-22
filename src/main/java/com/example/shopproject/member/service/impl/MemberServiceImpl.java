package com.example.shopproject.member.service.impl;


import com.example.shopproject.exception.MemberException;
import com.example.shopproject.member.dto.MemberAuth;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.member.service.MemberService;
import com.example.shopproject.main.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

/*
 * 1. 회원 가입
 * 2. 중복 회원 검증
 * 3. 로그인
*/

public class MemberServiceImpl implements UserDetailsService, MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }


    // 회원 가입
    /**
     * 1. 해당 이메일의 MemberEntity 존재하는지
     * 2. 동일 nickName 존재하는지
     * 3. 만약 예외 없다면 비밀번호를 encoding 해서 저장
     */
    public MemberEntity signUp(MemberAuth.SignUp request){

        registerValidation(request);

         return  memberRepository.save(MemberEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .password(this.passwordEncoder.encode(request.getPassword()))
                .nickName(request.getNickName())
                .phone(request.getPhone())

                .build());

    }
    private void registerValidation(MemberAuth.SignUp request) {
        if(memberRepository.existsByEmail(request.getEmail())){
            throw new MemberException(ErrorCode.EMAIL_ALREADY_EXIST);

        }

        if(memberRepository.countAllByNickName(request.getNickName()) > 0){
            throw new MemberException(ErrorCode.NICKNAME_ALREADY_EXISTS);

        }
    }


}
