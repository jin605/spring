package com.beyond.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {
    // @Slf4j 어노테이션으로 아래의 선언문은 생략이 가능하다.
    //private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home() {

        log.info("home() 메소드 호출 - info");
        log.debug("home() 메소드 호출 - debug");
        log.error("home() 메소드 호출 - error");
        // 뷰의 이름을 반환한다.
        return "home";
    }
}
