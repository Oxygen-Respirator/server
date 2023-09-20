package com.oxygen.oxygenspring.domain.user.jwt.provider;

import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring.domain.user.jwt.JwtAuthToken;
import com.oxygen.oxygenspring.domain.user.jwt.utils.JwtHeaderUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;

import java.security.Key;
import java.util.ArrayList;


@Log4j2
public class JwtAuthTokenProvider {

    public static final Long TOKEN_VALID_TIME = 50 * 365 * 24 * 2 * 30 * 60 * 1000L;
    private final Key key;


    public JwtAuthTokenProvider(String secretKey) {
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // token 생성
    public JwtAuthToken createJwtAuthToken(String userId, String userNickname) {
        return new JwtAuthToken(key, TOKEN_VALID_TIME, userId, userNickname);
    }


    public JwtAuthToken convertAccessToken(HttpServletRequest request) {
        String accessToken = JwtHeaderUtil.getAccessToken(request);
        return accessToken == null ? null : new JwtAuthToken(key, accessToken);
    }

    // 인증 객체 생성
    public Authentication getAuthentication(JwtAuthToken authToken) {
        // 유효한 토큰일 때
        if (authToken.tokenValidate()) {
            Claims claims = authToken.getTokenClaims();
            log.debug("claims subject : [{}]", claims.getSubject());

            User principal = new User(claims.get("userId").toString(), "", new ArrayList<>()); // userDetails 의 user 객체. username, password, authorities 세팅

            // 인증이 끝나고 SecurityContextHolder.getContext에 등록될 Authentication(인증) 객체
            return new UsernamePasswordAuthenticationToken(principal, authToken, new ArrayList<>());
        } else {
            throw new ApiException(ResponseCode.TOKEN_VALID_FAILED);
        }
    }

}
