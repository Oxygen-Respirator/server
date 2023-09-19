package com.oxygen.oxygenspring.domain.user.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.security.Key;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
public class JwtAuthToken {

    private final Key key;

    @Getter
    private final String token;

    public JwtAuthToken(Key key, Long expiry, String userId, String userNickname) {
        this.key = key;
        this.token = this.createJwtAuthToken(userId, userNickname, expiry);
    }

    private String createJwtAuthToken(String userId, String userNickname, Long expiry) {
        return Jwts.builder()
                .setSubject(userId)
                .setSubject(userNickname)
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(new Date().getTime() + expiry))
                .compact();
    }

    public boolean tokenValidate() {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
        return !claimsJws.getBody().isEmpty();
    }

    public Claims getTokenClaims() {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Claims getExpiredTokenClaims() {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (SignatureException e) {
            log.info("잘못된 JWT 서명입니다.");
        } catch (MalformedJwtException e) {
            log.info("유효하지 않은 구성의 JWT 토큰입니다.");
        } catch (UnsupportedJwtException e) {
            log.info("지원되지 않는 형식이나 구성의 JWT 토큰입니다.");
        } catch (IllegalArgumentException e) {
            log.info(e.toString().split(":")[1].trim());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.");
            return e.getClaims();
        }
        return null;
    }
}
