package com.beyond.university.config;

import com.beyond.university.auth.handler.AuthenticationFailureHandlerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // UserDetailsService
    //  - 전달받은 정보를 통해 사용자를 찾아 UserDetails 객체를 생성 후 반환한다.
    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            AuthenticationFailureHandler authenticationFailureHandler) throws Exception {

        // CSRF(Cross-Site Request Forgery)
        //  - 공격자가 사용자의 브라우저를 악용하여 인증된 세션을 가진 사용자의 권한으로 악성 요청을 보내는 공격
        //  - 스프링 시큐리티는 기본적으로 CSRF 보호 기능을 활성화하여 GET 요청을 제외한 요청에 대해 CSRF 토큰을 검증한다.
        httpSecurity
                .csrf(auth -> auth.disable()) // CSRF 설정 비활성화(JWT일떄는 뺀다)
                .csrf(Customizer.withDefaults()) // CSRF 기본 설정 사용
                .httpBasic(Customizer.withDefaults()) // http 인증
                // 폼 로그인 확설화
                .formLogin(formlogin ->
                        formlogin
                                .loginPage("/login")
                                //.usernameParameter("userId")
                                //.passwordParameter("userPwd")
                                .failureHandler(authenticationFailureHandler)

                        )
                // 기억하기 기능
                .rememberMe(rememberMe ->
                        rememberMe
                                .key("beyond") // 토큰 생성 시 서명에 사용되는 키 설정
                                .tokenValiditySeconds(3600) // 쿠키의 유효시간을 지정(초)
                )
                // 세션 관리 기능
                // 로그인 세션 1개만 유지
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .invalidSessionUrl("/login?invalid")
                                .maximumSessions(1)
                                .expiredUrl("/login?expired")
                )
                // 에러 핸들러 설정
                .exceptionHandling(exceptionHandling ->
                        // 권한이 없는 계정에서 잘못된 접근 시 이동할 URL을 지정한다.
                        exceptionHandling
                                .accessDeniedPage("/access-denied")
                )
                // 접급 제어 설정
                .authorizeHttpRequests(authorizationRequest ->
                        authorizationRequest
                                .requestMatchers("/js/**","/css/**","/images/**").permitAll() // 정적 리소스 허용
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/user/**").hasAnyRole("ADMIN","USER")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                // 로그아웃 설정
                .logout(logout ->
                        logout
                                .logoutUrl("/logout")
                                .logoutSuccessUrl("/login?logout")
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                );


        return httpSecurity.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        // 인 메모리 방식 설정 (테스트 용도로 사용)
//        UserDetails user = User.builder()
//                .username("user")
//                //.password("{noop}1234")
//                .password(passwordEncoder.encode("1234"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                //.password("{noop}5678")
//                .password(passwordEncoder.encode("5678"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

        provider.setPasswordEncoder(passwordEncoder);



        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {

        return new AuthenticationFailureHandlerImpl();
    }
}
