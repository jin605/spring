package com.beyond.aop.weapon;

import com.beyond.aop.annotation.NoLogging;
import com.beyond.aop.annotation.Repeat;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.annotation.Repeatable;

@Component
public class Sword extends Weapon {
    public Sword(@Value("크리스탈 소드") String name) {
        super(name);
    }

    @Override
    @NoLogging
    // @Repeat
    // 속성명이 value일 때만 속성명을 생략하고 값을 전달할 수 있다.
    // @Repeat("반복 횟수 지정")
    @Repeat(value = "반복 횟수 지정", count = 3)
    public String attack() {
        return "검을 휘두른다.";
    }

    @Override
    public String toString() {
        return "Sword{" +
                "name='" + name + '\'' +
                '}';
    }
}
