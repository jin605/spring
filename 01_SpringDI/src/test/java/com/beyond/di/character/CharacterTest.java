package com.beyond.di.character;

import com.beyond.di.config.RootConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
//@ContextConfiguration(locations = "classpath:spring/root-context.xml")
 @ContextConfiguration(classes = RootConfig.class)
@DisplayName("Character 클래스 테스트")
class CharacterTest {

    /*
        @Autowired 어노테이션을 통해서 주입받을 빈이 존재하지 않으면 에러가 발생한다.
            - required 속성은 빈 주입이 필수로 진행되어야 하는지 설정하는 속성이다. (기본값 : true)
            - required 속성이 true일 경우에 주입해야하는 빈이 애플리케이션 컨텍스트에 존재하지 않으면 에러 발생
            - required 속성이 false일 경우 주입해야하는 빈이 애플리케이션 컨테스트에 존재하지 않으면 null 주입
     */

    @Autowired(required = false)
    private Character character;

    @Value("${db.driver:not found}")
    private String driver;

    @Value("${db.url:not found}")
    private String url;

    @Test
    @Disabled
    @DisplayName("실행 환경 테스트")
    void nothing() {

    }

    @Test
    @DisplayName("Character 객체 생성 테스트")
    void create() {
        System.out.println(character);

        assertThat(character).isNotNull();
        assertThat(character.getWeapon()).isNotNull();

    }

    @Test
    @DisplayName("driver.properties 파일 테스트")
    void propertyTest() {
        System.out.println(driver + ", " + url);
    }

}