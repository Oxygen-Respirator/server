package com.oxygen.oxygenspring.domain.user.jwt.cofig;

import com.oxygen.oxygenspring.domain.user.jwt.provider.JwtAuthTokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Value("${keys.jwt.secret-key}")
    private String secret;

    @Bean
    public JwtAuthTokenProvider jwtProvider() {
        return new JwtAuthTokenProvider(secret);
    }
}
