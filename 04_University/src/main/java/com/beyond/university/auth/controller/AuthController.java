package com.beyond.university.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController
public class AuthController {

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login(ModelAndView modelAndView) {

        // 매번 랜덤 한 솔트 값을 가지거 암호화 하기 떄문에 매번 다른 값으로 암호화된다.
        // System.out.println(passwordEncoder.encode("1234"));
        // System.out.println(passwordEncoder.encode("5678"));

        // matches() 메소드를 사용하면 솔트 값을 떈 나머지 값과 원문을 비교한다.
        // System.out.println(passwordEncoder.matches("1234","$2a$10$tTUdf3P9eG/ku/w40IkLvuwcp9H1.qHzl4ibHD11Jye8tUZcxc4a2"));

        log.info("로그인 페이지 요청");
        modelAndView.setViewName("auth/login");

        return modelAndView;
    }



}
