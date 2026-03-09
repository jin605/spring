package com.beyond.di.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 해당 클래스가 자바 설정 파일임을 선언한다.
@Configuration
@Import(value = {PetConfig.class, OwnerConfig.class})
public class RootConfig {



}
