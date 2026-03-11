package com.beyond.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String home() {

        // 뷰의 이름을 반환한다.
        return "home";
    }

}
