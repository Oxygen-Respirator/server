package com.oxygen.oxygenspring.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic().disable() // 기본 인증 비활성화
                .formLogin().disable() // 기본 로그인 폼 비활성화
                .csrf().disable() // csrf 보안토큰 비활성화
                .sessionManagement().disable(); // 세션 유저 관리 비활성화 (세션 사용 시 별도 구현 필요)

        http.authorizeHttpRequests().anyRequest().permitAll();

        return http.build();
    }
}