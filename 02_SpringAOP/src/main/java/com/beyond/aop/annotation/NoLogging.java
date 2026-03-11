package com.beyond.aop.annotation;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.*;

/*
    어노테이션
        - jdk 1.5부터 추가된 기능을 자바 코드에 추가적인 정보를 제공하는 메타 데이터이다.
        - 비즈니스 로직에 영향을 주지 않지만 컴파일 과정에서 유효성 체크, 코드를 어떻게 컴파일하고 처리할지 알려주는 정보를 제공한다,
        - 어노테이션은 클래스, 메소드, 필드, 매개변수 등에 추가할 수 있다.
        -
 */
// 어노테이션을 적용한 위치를 지정한다.
@Target({METHOD, FIELD})
// 어노테이션의 유효 범위를 지정한다.
// @Retention(SOURCE) // 소드 코드에서만 유효하다.
// @Retention(CLASS) // 클래스 파일까지 유효하다
@Retention(RUNTIME) // 실행 시까지 유효하다.
// 부모 클래스에서 어노테이션을 선언하면 자식 클래스에도 상속한다.
// @Inherited
public @interface NoLogging {



}
