package com.beyond.aop.character;

import com.beyond.aop.config.RootConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RootConfig.class)
class CharacterTest {

    @Autowired
    private Character character;

    @Test
    @Disabled
    @DisplayName("실행 환경 테스트")
    void nothing() {
    }

    @Test
    @DisplayName("Character 객체 생성 테스트")
    void create() {

        // System.out.println(character);

        assertThat(character).isNotNull();
        assertThat(character.getWeapon()).isNotNull();
    }

    @Test
    @DisplayName("quest() 메소드 테스트")
    void questTest() {

        // 프록시 객체 확인
        // System.out.println(character.getClass());

        // System.out.println(character.quest("도토리 줍기"));
        // character.quest("도토리 줍기");

        assertThat(character.quest("도토리 줍기")).isNotNull().contains("도토리 줍기");


    }

    @Test
    @DisplayName("attack() 메소드 테스트")
    void attackTest() {

        assertThat(character.getWeapon()).isNotNull();
        assertThat(character.getWeapon().attack()).isNotNull();

    }

}
