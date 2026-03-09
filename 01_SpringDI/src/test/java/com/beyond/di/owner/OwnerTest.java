package com.beyond.di.owner;

import com.beyond.di.config.RootConfig;
import com.beyond.di.pet.Cat;
import com.beyond.di.pet.Dog;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

// JUnit에서 스프링을 사용할 수 있도록 SpringExtension.class 사용하여 확장한다.
@ExtendWith(SpringExtension.class)
// @ContextConfiguration을 통해서 설정 파일을 읽고 애플리케이션 컨텍스트를 생성한다.
// @ContextConfiguration(locations = "classpath:spring/root-context.xml")
@ContextConfiguration(classes = RootConfig.class)
@DisplayName("Owner 클래스 테스트")
class OwnerTest {

    @Autowired
    @Qualifier("lee")
    private Owner owner;

    @Test
    @Disabled
    @DisplayName("실행 환경 테스트")
    void nothing() {

    }

    @Test
    @DisplayName("Owner 객체 생성 테스트")
    void create() {
        // 기존에 자바 애플맄이션에서는 다형성을 통해 객체간의 결합도를 느슨하게 만들어준다.
        // 생성자를 통한 의존성 주입!
//        Owner owner = new Owner("홍길동",34,new Dog("댕댕이"));
        // Owner owner = new Owner("홍길동",34,new Cat("나비"));

        // 메서드를 통한 의존성 주입
        Owner owner = new Owner();

        owner.setName("이몽룡");
        owner.setAge(24);
        //owner.setDog(new Dog("멍멍이"));
        owner.setPet(new Cat("야옹이"));

        System.out.println(owner);
//        System.out.println(owner.getDog());
//        System.out.println(owner.getDog().bark());

        assertThat(owner).isNotNull();
        assertThat(owner.getPet()).isNotNull();
    }

    @Test
    @DisplayName("GenericXmlApplicationContext 테스트")
    void genericXmlApplicationContext() {

        ApplicationContext context =
                // new GenericXmlApplicationContext("spring/root-context.xml");
                new GenericXmlApplicationContext("classpath:spring/root-context.xml");
                // new GenericXmlApplicationContext("file:spring/root-context.xml");

        // Owner owner = (Owner) context.getBean("hong");
        // Owner owner = context.getBean("hong", Owner.class);
        Owner owner = context.getBean("lee", Owner.class);

        System.out.println(owner);

        assertThat(context).isNotNull();
        assertThat(owner).isNotNull();
        assertThat(owner.getPet()).isNotNull();


    }

    @Test
    @DisplayName("AnnotationConfigApplicationContext 테스트")
    void annotationConfigApplicationContext() {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(RootConfig.class);

        //Owner owner = context.getBean("hong", Owner.class);
        Owner owner = context.getBean("lee", Owner.class);
        System.out.println(owner);

        assertThat(context).isNotNull();
        assertThat(owner).isNotNull();
        assertThat(owner.getPet()).isNotNull();
    }

    @Test
    @DisplayName("autoWired 테스트")
    void autowiredTest() {
        // System.out.println(owner);

        assertThat(owner).isNotNull();
        assertThat(owner.getPet()).isNotNull();

    }


}