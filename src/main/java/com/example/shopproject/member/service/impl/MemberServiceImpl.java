package com.example.shopproject.member.service.impl;



import com.example.shopproject.common.mail.Mail;
import com.example.shopproject.common.mail.MailComponents;
import com.example.shopproject.common.type.ErrorCode;
import com.example.shopproject.common.type.Role;
import com.example.shopproject.common.type.UserStatus;
import com.example.shopproject.member.dto.*;
import com.example.shopproject.member.dto.MemberAuth.SignIn;
import com.example.shopproject.member.dto.MemberAuth.SignUp;
import com.example.shopproject.member.dto.MemberFindPassword.Request;
import com.example.shopproject.member.dto.MemberFindPassword.Response;
import com.example.shopproject.member.entity.MemberEntity;
import com.example.shopproject.member.exception.MemberException;
import com.example.shopproject.member.repostory.MemberRepository;
import com.example.shopproject.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.shopproject.common.type.UserStatus.*;

@Slf4j
@Service
@RequiredArgsConstructor

/*
 * 1. 회원 가입
 * 2. 중복 회원 검증
 * 3. 로그인
 */

public class MemberServiceImpl implements MemberService, UserDetailsService {

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
    public MemberDto signUp(SignUp request) {

        signUpValidation(request);

        String uuid = UUID.randomUUID().toString();
        MemberEntity memberEntity = memberRepository.save(MemberEntity.builder()
                                                                      .email(request.getEmail())
                                                                      .name(request.getName())
                                                                      .password(this.passwordEncoder.encode(request.getPassword()))
                                                                      .phone(request.getPhone())
                                                                      .emailAuthYn(false)
                                                                      .emailAuthKey(uuid)
                                                                      .userStatus(REQ)
                                                                      .build());

        sendMail(request, uuid);
        return MemberDto.fromEntity(memberEntity);
    }

    @Override
    @Transactional
    public boolean emailAuth(String uuid) {

        Optional<MemberEntity> optionalMember =
                memberRepository.findByEmailAuthKey(uuid);

        boolean result = true;

        if (optionalMember.isEmpty()) {
            throw new MemberException(ErrorCode.USER_NOT_FOUND);
        }

        MemberEntity member = optionalMember.get();

        // 이메일 인증 이미 완료
        if (member.isEmailAuthYn()) {
            return false;
        }

        member.setEmailAuthYn(true);
        member.setEmailAuthAt(LocalDateTime.now());
        member.setUserStatus(USING);
        memberRepository.save(member);

        return result;

    }

    private void sendMail(Object object, String uuid) {

        Mail email = new Mail(object, uuid);
        mailComponents.sendMail(email);
    }

    private void signUpValidation(SignUp request) {

        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new MemberException(ErrorCode.EMAIL_ALREADY_EXIST);
        }

        if (memberRepository.existsByNickName(request.getNickName())) {
            throw new MemberException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        MemberEntity memberEntity = memberRepository.findByEmail(email)
                                    .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        // 이메일 인증 아직 안된 경우
        if (!memberEntity.isEmailAuthYn()) {
            throw new MemberException(ErrorCode.MEMBER_NOT_EMAIL_AUTH);
        }

        // 회원 이용 정지 상태인 경우

        if (memberEntity.getUserStatus().equals(STOP)) {
            throw new MemberException(ErrorCode.MEMBER_STATUS_IS_STOP);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        if ((memberEntity.getRole()).equals(Role.ADMIN)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new User(memberEntity.getEmail(), memberEntity.getPassword(), grantedAuthorities);
    }


    @Transactional
    public MemberDto authentication(SignIn member) {

        var result = memberRepository.findByEmail(member.getEmail())
                                     .orElseThrow(() -> new UsernameNotFoundException("존재 하지 않는 ID 입니다."));

        // 존재하는 경우
        if (!this.passwordEncoder.matches(member.getPassword(), result.getPassword())) {
            throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
        }


        return MemberDto.fromEntity(result);
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

        // 1. 입력받은 비밀번호와 확인 비밀번호가 동일한지 확인
        // 2. ResetPassword 혹은 ResetPasswordLimitDt null 인지
        // 3. 기존 비밀번호랑 새 비밀번호가 동일한지
        // 4. ResetPassword 가 유효한지(기간 및 null)

        if (!request.getCheckPassword().equals(request.getPassword())) {
            throw new MemberException(ErrorCode.PASSWORD_NOT_MATCH);
        }

        return MemberResetPassword.Response.fromEntity(resetPasswordValidation(request));
    }

    private MemberEntity resetPasswordValidation(MemberResetPassword.Request request) {

        MemberEntity memberEntity
                = memberRepository.findByResetPasswordKey(request.getUuid())
                                  .orElseThrow(() -> new MemberException(
                                          ErrorCode.RESET_PASSWORD_ERROR)
                                  );

        // 변경하고자 하는 비밀번호 == 기존 비밀번호
        if (Objects.equals(request.getPassword(), memberEntity.getPassword())) {
            throw new MemberException(ErrorCode.RESET_PASSWORD_ERROR);
        }

        // ResetPassword 혹은 ResetPassword 기한 정보가 없는 경우
        if (memberEntity.getResetPasswordLimitDate() == null
                || memberEntity.getResetPasswordKey() == null) {

            throw new MemberException(ErrorCode.RESET_PASSWORD_ERROR);
        }

        // ResetPassword 기한이 지난 경우
        if (memberEntity.getResetPasswordLimitDate().isBefore(LocalDateTime.now())) {
            throw new MemberException(ErrorCode.INVALID_DATE);
        }

        memberEntity.setPassword(passwordEncoder.encode(request.getPassword()));
        memberEntity.setResetPasswordKey(null);
        memberEntity.setResetPasswordLimitDate(null);

        return memberRepository.save(memberEntity);
    }


    /**
     * 관리자 기능
     * 모든 회원 목록
     */

    @Override

    public List<MemberDto> getMemberList(Pageable pageable) {

        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        int size = pageable.getPageSize();

        return memberRepository.findAll(pageable)
                               .stream()
                               .map(MemberDto::fromEntity)
                               .collect(Collectors.toList());

    }

    @Override
    public MemberSetRole.Response setRole(MemberSetRole.Request request) {

        MemberEntity memberEntity
                = memberRepository.findByEmail(request.getEmail())
                                  .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        Role preRole = memberEntity.getRole();

        memberEntity.setRole(request.getRole());
        return MemberSetRole.Response.fromEntity(
                memberRepository.save(memberEntity), preRole
        );
    }

    @Override
    public MemberDto detail(String email) {

        MemberEntity memberEntity
                = memberRepository.findByEmail(email)
                                  .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        return MemberDto.fromEntity(memberEntity);
    }

    @Override
    public MemberStatus.Response setUserStatus(MemberStatus.Request request) {

        MemberEntity memberEntity
                = memberRepository.findByEmail(request.getEmail())
                                  .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        UserStatus preUserStatus = memberEntity.getUserStatus();
        memberEntity.setUserStatus(request.getUserStatus());

        return MemberStatus.Response.fromEntity(memberRepository.save(memberEntity), preUserStatus);
    }

    @Override
    public MemberPassword.Response setUserPassword(MemberPassword.Request request) {


        MemberEntity memberEntity
                = memberRepository.findByEmail(request.getEmail())
                                  .orElseThrow(() -> new MemberException(ErrorCode.USER_NOT_FOUND));

        String prePassword = memberEntity.getPassword();
        memberEntity.setPassword(passwordEncoder.encode(request.getPassword()));

        return MemberPassword.Response.fromEntity(
                memberRepository.save(memberEntity), prePassword);
    }

}
