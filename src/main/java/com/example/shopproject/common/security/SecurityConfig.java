package com.example.shopproject.common.security;


import com.example.shopproject.common.type.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private final JwtAuthenticationFilter jwtAuthenticationFiler;


    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // JWT 토큰을 사용하기에 Session 방싱 사용 x
            .and().authorizeRequests()
            .antMatchers("/", "/member/signup",
                    "/member/signin",
                    "/member/email-auth",
                    "/member/find/password")
            .permitAll()
            .antMatchers("/admin/**").permitAll()
            .and().addFilterBefore(this.jwtAuthenticationFiler, UsernamePasswordAuthenticationFilter.class);


        // 관리자 역할인 경우
        // 에러인 경우 이동할 페이지(임의로 결정)
        http.exceptionHandling()
            .accessDeniedPage("/error/denied");
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
