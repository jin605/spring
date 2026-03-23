package com.beyond.university.auth.jwt;

import com.beyond.university.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/*
    JwtUtil
        - JWT와 관련된 유틸리티 역할을 하는 클래스이다.
        - JWT 토큰 생성, 클래임 파싱, 유효성 검사 등을 수행한다.
 */
@Slf4j
@Component
public class JwtUtil {

    private final String issuer;
    private final SecretKey secretKey;

    public JwtUtil(JwtProperties jwtProperties) {
        log.info("JWT Issuer : {}", jwtProperties.getIssuer());
        log.info("JWT Secret : {}", jwtProperties.getSecret());

        this.issuer = jwtProperties.getIssuer();
        this.secretKey = new SecretKeySpec(
                jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8),
                Jwts.SIG.HS256.key().build().getAlgorithm()
        );
    }

    // JWT(JSON Web Token)
    public String createJwtToken(Map<String, Object> claims, long expiration) {

        return Jwts.builder()

                .header().add("typ", "JWT").and() // typ 헤더 추가
                .claims(claims) // 공개 클레임
                .id(Long.toHexString(System.nanoTime())) // jti(JWT ID) 클래임
                .issuer(this.issuer) // 빌급 주체
                .issuedAt(new Date()) // 발급 시간
                .expiration(new Date(System.currentTimeMillis() + expiration)) // 만료 시간
                .signWith(secretKey) // 서명을 생성
                .compact();

    }

    public long getIssuedAt(String accessToken) {

        return getClaims(accessToken).getIssuedAt().getTime();
    }

    public long getExpiredAt(String accessToken) {

        return getClaims(accessToken).getExpiration().getTime();
    }

    public Claims getClaims (String accessToken) {

        // 토큰이 만료되면 parseSignedClaims() 메소드에서
        // ExpiredJwtException 예외가 발생하여 코드가 실행되지 않기 때문에
        // ExpiredJwtException 예외가 발생해도 클래임을 반환하도록 예외 처리를 한다.
        try {
            return Jwts
                    .parser()
                    .verifyWith(secretKey)
                    .build()
                    .parseSignedClaims(accessToken)
                    .getPayload();

        } catch (ExpiredJwtException e) {

            return e.getClaims();

        }


    }
}
