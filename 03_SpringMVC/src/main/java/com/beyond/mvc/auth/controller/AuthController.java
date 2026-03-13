package com.beyond.mvc.auth.controller;

import com.beyond.mvc.auth.model.dto.UserResponseDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;

@Slf4j
@Controller
// @RestController
public class AuthController {

    // @RequestMapping
    //  - 컨트롤러가 처리할 요청을 정의한다. (URL, Method 등)
    //  - method 속성을 지정하지 않으면 모든 HTTP 요청 방식을 처리한다.
    // @RequestMapping("/auth/login")
    // @RequestMapping(value = "/auth/login", method = RequestMethod.POST)
    // @GetMapping("/auth/login")
    // @PostMapping("/auth/login")
    // public String login() {
    //
    //     log.info("login 요청");
    //
    //     // return "home";
    //     return "redirect:/";
    // }

    // 요청 시 사용자의 파라미터를 전송받는 방법
    // 1. HttpServletRequest 객체를 통해서 전송받는 방법 (Servlet/JSP 방식)
    // @PostMapping("/auth/login")
    // public String login(HttpServletRequest request, HttpSession session) {
    //     String username = request.getParameter("username");
    //     String password = request.getParameter("password");
    //
    //     log.info("username = {}, password = {}", username, password);
    //     log.info("SessionID = {}", session.getId());
    //
    //     return "redirect:/";
    // }

    // 2.1. @RequestParam 어노테이션으로 전송받는 방법
    //  - 스프링에서는 간편하게 파라미터를 받아올 수 있는 @RequestParam 어노테이션을 제공한다.
    //  - 매개변수의 이름과 파라미터의 이름이 동일하면 @RequestParam 어노테이션을 생략할 수 있다.
    // @PostMapping("/auth/login")
    // // public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
    // // public String login(@RequestParam String username, @RequestParam String password) {
    // public String login(String username, String password) {
    //
    //     log.info("username = {}, password = {}", username, password);
    //
    //     return "redirect:/";
    // }

    // 2.2. @RequestParam 어노테이션의 required 속성과 defaultValue 속성
    //  - @RequestParam 어노테이션을 사용하는 경우 존재하지 않는 파라미터를 받으려고 하면 에러가 발생한다.
    //  - @RequestParam 어노테이션에 required 속성의 값을 false로 지정하면 에러가 아닌 null 값을 넘겨준다.
    //  - @RequestParam 어노테이션에 defaultValue 속성을 사용하면 파리미터에 값이 없을 경우 기본값을 지정할 수 있다.
    // @PostMapping("/auth/login")
    // public String login(@RequestParam String username,
    //                     @RequestParam String password,
    //                     // @RequestParam String address) {
    //                     // @RequestParam(required = false) String address) {
    //                     @RequestParam(defaultValue = "서울특별시") String address) {
    //
    //         log.info("username = {}, password = {}, address : {}", username, password, address);
    //
    //         return "redirect:/";
    // }

    // 2.3. 동일한 이름의 파라미터가 여러 개 전달되는 경우
    //  - 동일한 이름의 파라미터가 여러 개 전돨되는 경우 문자열이나 배열, 리스트로 값을 전달받을 수 있다.
    // @PostMapping("/auth/login")
    // public String login(@RequestParam String username, @RequestParam String password,
    //                     // @RequestParam("hobby") String hobbies) {
    //                     // @RequestParam("hobby") String[] hobbies) {
    //                     @RequestParam("hobby") List<String> hobbies) {
    //
    //     // log.info("username = {}, password = {}, hobbies : {}", username, password, hobbies);
    //     // log.info("username = {}, password = {}, hobbies : {}", username, password, Arrays.toString(hobbies));
    //     log.info("username = {}, password = {}, hobbies : {}", username, password, hobbies);
    //
    //     return "redirect:/";
    // }

    // 3. 객체 타입으로 파라미터를 전송받는 방법
    //  - 요청 파라미터가 많은 경우 객체 타입으로 파라미터를 넘겨받는 방법이다.
    // @PostMapping("/auth/login")
    // public String login(LoginRequestDto requestDto) {
    //
    //     log.info("LoginRequestDto : {}", requestDto);
    //
    //     return "redirect:/";
    // }

    // 4. @PathVariable 어노테이션을 통해서 전송받는 방법
    //  - URL 경로상에 있는 특정 값을 가져오기 위해 사용하는 방법이다.
    //  - REST API를 사용할 때 요청 URL 상에서 필요한 값을 가져오는 경우에 주로 사용한다.
    //  - 매핑 URL에 {}를 이용해서 변수명을 지정하고 @PathVariable을 이용해서 변수의 값을 가져온다.
    //  - Path Variable 이름과 매개변수 이름이 동일하면 @PathVariable의 value 속성은 생략이 가능하다.
    // @GetMapping("/auth/users/{no}")
    // // public String users(@PathVariable("no") String no) {
    // public String users(@PathVariable String no) {
    //
    //     log.info("User No : {}", no);
    //
    //     return "redirect:/";
    // }

    // 뷰에 데이터를 전달하는 방법
    // 1. Model 객체 사용
    //  - Model이라는 객체는 컨트롤러에서 데이터를 뷰로 전달하고자 할 때 사용하는 객체이다.
    //  - 전달하고자 하는 데이터를 맵 형식(key, value)으로 담을 수 있다.
    //  - Model 객체의 Scope는 Request이다.
    // @GetMapping("/auth/users/{no}")
    // public String users(Model model, @PathVariable int no) {
    //
    //     log.info("User No : {}", no);
    //     model.addAttribute("user",new UserResponseDto(no, "hong123", "서울특별시 동작구"));
    //
    //     return "auth/profile";
    // }

    // 2. ModelAndView 객체 사용
    //  - 컨트롤러에서 뷰로 전달할 데이터와 뷰에 대한 정보를 담는 객체이다.
    // @GetMapping("/auth/users/{no}")
    // public ModelAndView users(ModelAndView modelAndView, @PathVariable int no) {
    //     UserResponseDto responseDto =
    //             new UserResponseDto(no, "lee123", "서울특별시 종로구");
    //
    //     modelAndView.addObject("user", responseDto);
    //     modelAndView.setViewName("auth/profile");
    //
    //     return modelAndView;
    // }

    // 클라이언트로 데이터를 전달하는 방법
    // 1. @ResponseBody 어노테이션을 사용하는 방법
    //   - 일반적으로 컨트롤러 메소드의 반환형이 String일 경우 뷰의 이름을 반환한다.
    //   - @ResponseBody 어노테이션이 붙은 메소드는 클라이언트에 직접 데이터를 반환할 수 있다.
    // @ResponseBody
    // @GetMapping("/data")
    // public String data() {
    //
    //     return "Hello World!";
    // }

    /*
        JSON (JavaScript Object Notation)
          - 자바스크립트 문법을 따르는 문자 기반의 데이터 포맷이다.
          - {} 안에 key: value로 구성한다.
          - key는 반드시 문자열을 사용한다.
          - value는 String, Number, Boolean, Array, Object, null을 사용한다.
          - 대부분의 프로그래밍 언어에서 JSON 데이터를 핸들링할 수 있는 라이브러리를 제공하고 있다.

        jackson 라이브러리
          - 자바 객체를 JSON 형태의 데이터로 변환하기 위한 라이브러리이다.
          - 스프링에서는 jackson 라이브러리를 추가하고 @ResponseBody을 사용하면
            컨트롤러의 메소드에서 리턴되는 객체를 자동으로 JSON 문자열로 변경해서 응답한다.
     */
    // @ResponseBody
    // @GetMapping("/auth/users/{no}")
    // public Object users(@PathVariable int no) {
    //     // Map<String, Object> map = new HashMap<>();
    //
    //     // map.put("no", no);
    //     // map.put("name", "홍길동");
    //     // map.put("isAdmin", true);
    //     // map.put("user", new UserResponseDto(no, "lee123", "서울특별시 종로구"));
    //     // map.put("numbers", new int[]{4, 5, 6, 7});
    //
    //     List<Object> list = new ArrayList<>();
    //
    //     list.add(null);
    //     list.add(no);
    //     list.add("hong123");
    //     list.add(false);
    //     list.add(new UserResponseDto(no, "lee123", "서울특별시 종로구"));
    //
    //     // return new UserResponseDto(no, "lee123", "서울특별시 종로구");
    //     // return map;
    //     return list;
    // }

    // 2. @RestController 어노테이션을 사용하는 방법
    //   - 해당 어노테이션이 붙은 클래스의 모든 메소드에서 데이터를 반환하는 경우 사용한다.
    //   - @RestController 어노테이션을 선언하면 클래스의 모든 메소드는 @ResponseBody 어노테이션이 기본으로 적용된다.
    // @GetMapping("/auth/users/{no}")
    // public Object users(@PathVariable int no) {
    //
    //     return new UserResponseDto(no, "sung123", "서울특별시 강남구");
    // }

    // 3. ResponseEntity 객체 사용
    //  - HttpEntity 클래스를 상속하는 클래스로 HTTP 응답 데이터를 포함하는 클래스이다.
    //  - 개발자가 직접 HTTP Body, Header, Status Code를 세팅해서 반환할 수 있다.

    // * HttpEntity
    //  - Spring에서 제공하는 클래스로 HTTP 요청 또는 응답에 해당하는 HTTP Header와 HTTP Body를 포함하는 클래스이다.
    @GetMapping("/auth/users/{no}")
    public ResponseEntity<UserResponseDto> users(@PathVariable int no) {
        UserResponseDto responseDto =
                new UserResponseDto(no, "lim123", "서울특별시 서초구");

        // return ResponseEntity.ok(responseDto);
        // return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer asdf7348a9df8as9f72348r7asdf7878cv")
                .body(responseDto);
    }

    // @ResponseBody와의 차이점
    // @GetMapping("/auth/users/{no}")
    // @ResponseBody
    // public UserResponseDto users(@PathVariable int no, HttpServletResponse response) {
    //     UserResponseDto responseDto =
    //             new UserResponseDto(no, "lim123", "서울특별시 서초구");
    //
    //     response.setStatus(200);
    //     response.setContentType("application/json");
    //     response.setHeader("Authorization", "Bearer asdf7348a9df8as9f72348r7asdf7878cv");
    //
    //     return responseDto;
    // }
}