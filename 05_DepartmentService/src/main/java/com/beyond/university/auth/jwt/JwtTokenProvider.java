package com.beyond.university.auth.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/*
    JwtTokenProvider
        - 엑세스 토큰(Access Token), 레프레시 토큰(Refresh Token), 인증 객체(Authentication)를 생성한다.
        - 레디스(Redis)에 토큰 저장, 조회, 삭제 등의 작업을 수행한다.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtUtil jwtUtil;
    private static final long ACCESS_TOKEN_EXPIRATION = 1000L * 60 * 30; // 30분

    public String createAccessToken(String username, List<String> authorities) {

        Map<String, Object> claims =
                Map.of("username", username, "authorities", authorities, "token_type", "access");


        return jwtUtil.createJwtToken(claims, ACCESS_TOKEN_EXPIRATION);
    }
}
