package com.example.shopproject.member.repostory;

import com.example.shopproject.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);
    Integer countAllByNickName(String nickname);
}
