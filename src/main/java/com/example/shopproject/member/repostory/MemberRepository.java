package com.example.shopproject.member.repostory;

import com.example.shopproject.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);
    boolean existsByNickName(String nickName);

    Optional<MemberEntity> findByEmail(String email);

    Optional<MemberEntity> findByEmailAuthKey(String emailAuthKey);

    Optional<MemberEntity> findByEmailAndName(String userEmail, String userName);

    Optional<MemberEntity> findByResetPasswordKey(String resetPasswordKey);
}
