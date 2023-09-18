package com.oxygen.oxygenspring.domain.user.jwt.filter;

import com.oxygen.oxygenspring._common.exception.ApiException;
import com.oxygen.oxygenspring._common.exception.responseCode.ResponseCode;
import com.oxygen.oxygenspring.domain.user.jwt.JwtAuthToken;
import com.oxygen.oxygenspring.domain.user.jwt.provider.JwtAuthTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtAuthTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        JwtAuthToken token = tokenProvider.convertAccessToken(request);

        try {

            if (token != null && token.tokenValidate()) {
                Authentication authentication = tokenProvider.getAuthentication(token);
                // SecurityContextHolder 에 인증 객체를 넣는다.
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                throw new ApiException(ResponseCode.ACCESS_TOKEN_NOT_EXIST);
            }
            
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
            request.setAttribute("exception", ResponseCode.WRONG_TYPE_SIGNATURE.getCode());
        } catch (MalformedJwtException e) {
            log.info("유효하지 않은 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", ResponseCode.WRONG_TYPE_TOKEN.getCode());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            request.setAttribute("exception", ResponseCode.EXPIRED_ACCESS_TOKEN.getCode());
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식이나 구성의 JWT 토큰입니다.");
            request.setAttribute("exception", ResponseCode.WRONG_TYPE_TOKEN.getCode());
        } catch (IllegalArgumentException e) {
            log.info(e.toString().split(":")[1].trim());
            request.setAttribute("exception", ResponseCode.INVALID_ACCESS_TOKEN.getCode());
        } catch (ApiException e) {
            log.info("Access Token이 존재하지 않습니다.");
            request.setAttribute("exception", ResponseCode.ACCESS_TOKEN_NOT_EXIST.getCode());
        }

        filterChain.doFilter(request, response);
    }
}
