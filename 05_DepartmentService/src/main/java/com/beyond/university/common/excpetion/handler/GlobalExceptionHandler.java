package com.beyond.university.common.excpetion.handler;

import com.beyond.university.common.excpetion.UniversityException;
import com.beyond.university.common.excpetion.dto.ApiErrorResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/*
    스프링 예외 처리

    1. @ControllerAdivce
        - Controller 빈에서 발생하는 예외를 전역으로 처리할 수 있다.

    2. @RestControllerAdivce
        - RestController 빈에서 발생하는 예외를 처리할 수 있다.

    3. @ExcpetionHandler
        -컨트롤러에서 발생하는 예외를 처리하는 매서드를 정의할 떄 사용한다.
        - 메소드에서 처리할 예외를 어노테이션의 value 속성을 지정한다.

 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UniversityException.class)
    public ResponseEntity<ApiErrorResponseDto> handleException (UniversityException e) {
        ApiErrorResponseDto apiErrorResponseDto = new ApiErrorResponseDto(
                e.getHttpStatus().value(),
                e.getStatus(),
                e.getMessage()
        );

        log.error("UniversityException : {}", e.getMessage());

        return new ResponseEntity<>(apiErrorResponseDto, e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDto> handleException(Exception e) {

        ApiErrorResponseDto responseDto = new ApiErrorResponseDto(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                e.getMessage()
        );

        log.error("Global Exception : {}", e.getMessage());


        return new ResponseEntity<>(responseDto,HttpStatus.INTERNAL_SERVER_ERROR);
    }








}
