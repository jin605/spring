package com.beyond.di.character;

import com.beyond.di.weapon.Weapon;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
/*
    character.properties 파일의 값을 읽어오는 방법

    1. character.properties 파일의 값을 읽어오도록 설정한다.
        - xml 설정 파일을 사용하는 경우 <context:property-placeholder/> 요소를 추가한다.
        - 자바 설정 파일을 사용하는 경우 PropertySourcesPlaceholderConfigurer 빈을 등록한다.

    2. 스프링 프로퍼티 플레이스 홀더를 사용해서 character.properties 파일의 값을 읽어 온다.
        - ${키:기본값}

 */

@Getter
@ToString
@Component
 @RequiredArgsConstructor
public class Character {

     @Value("${character.name:홍길동}")
    private String name;

     @Value("${character.level:77}")
    private int level;

    // 1. 필드에 빈을 주입 받는 방법
    // @Autowired
    // @Qualifier("sword")
    // private Weapon weapon;

    // 2. Setter 메소드로 빈을 주입 받는 방법
    // 2-1) 직접 Setter 메소드 생성

    // public void setWeapon(@Autowired Weapon weapon) {
    //     this.weapon = weapon;
    // }

    // 2-2) Lombok 어노테이션 사용
    // @Setter(onMethod_ = @Autowired)
    // private Weapon weapon;

    // 3. 생성자로 빈을 주입받는 방법
    // 3-1) 직접 생성자 생성
    // public Character(/* @Autowired */ Weapon weapon) {
    //     this.weapon = weapon;
    // }

    // 3-2)Lombok 어노테이션 사용
    private final Weapon weapon;


}
