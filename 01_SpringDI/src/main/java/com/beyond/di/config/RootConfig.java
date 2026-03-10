package com.beyond.di.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

// 해당 클래스가 자바 설정 파일임을 선언한다.
@Configuration
@Import(value = {PetConfig.class, OwnerConfig.class})
// 컴포넌트 스캐닝 활성화
@ComponentScan("com.beyond.di")
public class RootConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer =
                new PropertySourcesPlaceholderConfigurer();

        // character.properties 파일의 값을 읽어오도록 설정한다.
        // configurer.setLocation(new ClassPathResource("character.properties"));
        configurer.setLocations(
                new ClassPathResource("character.properties"),
                new ClassPathResource("driver.properties"));

        return configurer;
    }



}
