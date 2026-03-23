package com.beyond.university.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity

                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                // JWT토큰으로 인증을 처리하기 때문에 세션을 사용하지 않는다.
                .sessionManagement(SessionManagemen ->
                        SessionManagemen.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizationRequest ->
                        authorizationRequest

                                // Swagger
                                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                                // 로그인, 토큰 재발급
                                .requestMatchers(HttpMethod.POST,"/api/v1/auth/login", "/api/v1/auth/refresh`").permitAll()
                                // 모든 GET 요청의 경우 허용
                                .requestMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
                                // 이 외의 모든 요청은 인증이 필요
                                .anyRequest().authenticated()
                );


        return httpSecurity.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }
}
