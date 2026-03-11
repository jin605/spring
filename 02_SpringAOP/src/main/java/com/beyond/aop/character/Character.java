package com.beyond.aop.character;

import com.beyond.aop.weapon.Weapon;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class Character {

     @Value("홍길동")
    private String name;

     @Value("77")
    private int level;

    @Autowired
    @Qualifier("sword")
    private Weapon weapon;

    public String quest(String questName) {

       // if (true) {
       //     throw new RuntimeException("Quest 수행 중 예외 발생");
       // }

        return String.format("%s 퀘스트 진행 중..\n", questName);

    }



}
