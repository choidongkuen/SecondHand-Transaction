package com.example.shopproject.member.entity;


import com.example.shopproject.common.type.Role;
import com.example.shopproject.common.type.UserStatus;
import com.example.shopproject.order.entity.OrderEntity;
import com.example.shopproject.product.entity.ProductEntity;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Slf4j
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
@Entity

public class MemberEntity extends BasicTimeEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(unique = true)
    private String email;

    private String name;

    private String phone;

    private String password;


    // 등록 상품
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<ProductEntity> products = new ArrayList<>();

    // 주문 상품
    @OneToMany(mappedBy = "memberEntity",cascade = CascadeType.ALL)
    private List<OrderEntity> orders = new ArrayList<>();

    @Column(unique = true)
    private String nickName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private UserStatus userStatus;

    // 이메일 인증 여부
    private boolean emailAuthYn;

    // 이메일 인증 키
    private String emailAuthKey;

    // 이메일 인증 일시
    private LocalDateTime emailAuthAt;

    // 비밀번호 초기화 키
    private String resetPasswordKey;

    // 비밀번호 초기화 키 유효 기간
    private LocalDateTime resetPasswordLimitDate;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
