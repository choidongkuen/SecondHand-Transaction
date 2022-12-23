package com.example.shopproject.member.service.impl;


import com.example.shopproject.common.mail.Mail;
import com.example.shopproject.common.mail.MailComponents;
import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.member.dto.MemberAuth.SignIn;
import com.example.shopproject.member.dto.MemberAuth.SignUp;
import com.example.shopproject.member.dto.MemberFindPassword.Request;
import com.example.shopproject.member.dto.MemberFindPassword.Response;
import com.example.shopproject.member.dto.MemberResetPassword;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.exception.MemberException;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor

/*
 * 1. 회원 가입
 * 2. 중복 회원 검증
 * 3. 로그인
 */

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailComponents mailComponents;


    // 회원 가입

    /**
     * 1. 해당 이메일의 MemberEntity 존재하는지
     * 2. 동일 nickName 존재하는지
     * 3. 만약 예외 없다면 비밀번호를 encoding & 이메일 인증 키  저장
     */

    @Transactional
    public MemberEntity signUp(SignUp request) {

        registerValidation(request);

        String uuid = UUID.randomUUID().toString();
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                              .email(request.getEmail())
                              .name(request.getName())
                              .password(this.passwordEncoder.encode(request.getPassword()))
                              .phone(request.getPhone())
                              .emailAuthYn(false)
                              .emailAuthKey(uuid)
                              .build());

        sendMail(request,uuid);
        return memberEntity;
    }

    @Override
    @Transactional
    public boolean emailAuth(String uuid) {

        Optional<MemberEntity> optionalMember =
                memberRepository.findByEmailAuthKey(uuid);

        boolean result = true;

        if (optionalMember.isEmpty()) {
            result = false;
            throw new MemberException(ErrorCode.USER_NOT_FOUND);
        }

        MemberEntity member = optionalMember.get();
        member.setEmailAuthYn(true);
        member.setEmailAuthAt(LocalDateTime.now());
        memberRepository.save(member);

        return result;

    }

    private void sendMail(Object object,String uuid) {

        Mail email = new Mail(object, uuid);
        mailComponents.sendMail(email);
    }

    private void registerValidation(SignUp request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(ErrorCode.EMAIL_ALREADY_EXIST);

        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByEmail(username)
                .orElseThrow( () -> new MemberException(ErrorCode.USER_NOT_FOUND));

        if(!memberEntity.isEmailAuthYn()){
            throw new MemberException(ErrorCode.MEMBER_NOT_EMAIL_AUTH);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(memberEntity.getEmail(), memberEntity.getPassword(), grantedAuthorities);
    }


    @Transactional
    public MemberEntity authentication(SignIn member){

        var result = memberRepository.findByEmail(member.getEmail())
                                     .orElseThrow(() -> new UsernameNotFoundException("존재 하지 않는 ID 입니다."));

        // 존재하는 경우
        if(!this.passwordEncoder.matches(member.getPassword(),result.getPassword())){
            throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        return result;
    }


    @Transactional
    @Override
    public Response findPassword(Request request) {

        MemberEntity memberEntity
                = memberRepository.findByEmailAndName(request.getEmail(), request.getName())
                                  .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        String uuid = UUID.randomUUID().toString();

        memberEntity.setResetPasswordKey(uuid);
        memberEntity.setResetPasswordLimitDate(LocalDateTime.now().plusDays(1));
        memberRepository.save(memberEntity);

        sendMail(request, uuid);

        return Response.fromEntity(memberEntity);
    }

    @Transactional
    @Override
    public MemberResetPassword.Response resetPassword
            (MemberResetPassword.Request request) {

        // 1. 유효기간이 지나지 않았는지(혹은 유효한지)
        // 2. 두 비밀번호가 동일한지

        if(!request.getCheckPassword().equals(request.getPassword())){
            throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        MemberEntity memberEntity
                = memberRepository.findByResetPasswordKey(request.getUuid())
                .orElseThrow(() -> new MemberException(ErrorCode.RESETPASSWORDKEY_UNMATCH));

        resetPasswordValidation(memberEntity);

        memberEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        memberEntity.setResetPasswordKey("");
        memberEntity.setResetPasswordLimitDate(null);
        memberRepository.save(memberEntity);

        return MemberResetPassword.Response.fromEntity(memberEntity);

    }

    private static void resetPasswordValidation(MemberEntity memberEntity) {

        if(memberEntity.getResetPasswordLimitDate() == null){
            throw new MemberException(ErrorCode.INVALID_DATE);
        }

        if(memberEntity.getResetPasswordLimitDate().isBefore(LocalDateTime.now())){
            throw new MemberException(ErrorCode.INVALID_DATE);
        }
    }
}
