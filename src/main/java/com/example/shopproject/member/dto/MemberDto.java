package com.example.shopproject.member.dto;

import com.example.shopproject.common.type.Role;
import com.example.shopproject.common.type.UserStatus;
import com.example.shopproject.member.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class MemberDto {

    private String email;
    private String name;
    private String password;
    private String phone;
    private String nickName;
    private Role role;
    private UserStatus userStatus;
    private boolean emailAuthYn;
    private LocalDateTime emailAuthAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDto fromEntity(MemberEntity entity){

        return MemberDto.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .phone(entity.getPhone())
                .password(entity.getPassword())
                .role(entity.getRole())
                .userStatus(entity.getUserStatus())
                .nickName(entity.getNickName())
                .emailAuthAt(entity.getEmailAuthAt())
                .emailAuthYn(entity.isEmailAuthYn())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
