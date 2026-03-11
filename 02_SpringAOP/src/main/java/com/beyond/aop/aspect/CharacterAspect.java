package com.beyond.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CharacterAspect {
    /*
        포인트 커트 표현식
            - 스프링 AOP에서 포인트 커트는 AspectJ의 포인트 커트 표현식을 이용해서 정의한다.
            - execution([접근제한자] 리턴타입 클래스이름.메소드이름([파라미터, ...]))
                - 메소드 실행에 대한 표현식이다.
                - * : 모든 값을 표현한다.
                - .. : 0개 이상을 의미한다.
            - args()
                - 대상 메소드에 전달되는 매개값을 어드바이스에 전달하기 위한 표현식이다.
            - bean()
                - 어드바이스를 적용할 빈의 ID를 지정하는 표현식이다.
            - annotation()
                - 주어진 에노테이션을 맞는 포인트에 어드바이슬르 적용하기 위한 표현식이다.


     */

    @Pointcut("execution(* com.beyond.aop.character.Character.quest(..)) && args(questName)")
    public void questPointcut(String questName) {

    }

    // @Before("execution(* com.beyond.aop.character.Character.quest(..))")
    // @Before("questPointcut()")
    // @Before(value = "questPointcut(questName)", argNames = "questName")
    public void beforeQuest(String questName) {

        // 퀘스트를 수행하기 전에 필요한 작업들을 작성한다.
        System.out.printf("%s 퀘스트 준비 중..\n",questName);
    }

    // @After("execution(* com.beyond.aop.character.Character.quest(..))")
    // @After("questPointcut()")
    // @After(value = "questPointcut(questName)", argNames = "questName")
    public void afterQuest(String questName) {

        // 퀘스트를 수행한 결과에 상관없이 필요한 작업들을 작성한다.
        System.out.printf("%s 퀘스트 수행 완료..\n",questName);
    }

    // @AfterReturning(value = "questPointcut(questName)", argNames = "questName")
    // @AfterReturning(
    //         value = "questPointcut(questName)",
    //         returning = "result",
    //         argNames = "questName, result"
    // )
    public void success(String questName, String result) {
        // 쿼스트가 정상적으로 완료된 후 필요한 작업들을 작성한다.
        System.out.printf("result : %s",result);
        System.out.printf("%s 퀘스트 수행 완료..\n", questName);

    }

    //@AfterThrowing(value = "questPointcut(questName)", argNames = "questName")
    // @AfterThrowing(
    //         value = "questPointcut(questName)",
    //         throwing = "exception",
    //         argNames = "questName, exception"
    // )
    public void fail(String questName, Exception exception) {

        // 퀘스트 수행 중 에러가 밸생했을 때 필요한 작업들을 작성한다.

        System.out.printf("message : %s\n",exception.getMessage());
        System.out.printf("%s 퀘스트 수행 중 에러가 발생했습니다..\n", questName);

    }

    @Around("execution(* com.beyond.aop.character.Character.quest(..))")
    public String around(ProceedingJoinPoint jp) {
        String result = null;
        // String questName = (String) jp.getArgs()[0];
        String questName = String.format("[%s]", jp.getArgs()[0]);


        try {
            // before 어드바이스에 대한 기능을 수행 (proceed를 기준)
            System.out.printf("%s 퀘스트 준비 중..\n",questName);

            // 티켓 객체의 메소드 호출
            // jp.proceed();

            // 타켓 객체의 메소드에 리턴값이 있는 경우
            // result = (String) jp.proceed();

            // 타켓 객체의 메소드에 파라미터 값을 변경해서 전달하는 경우
            result = (String) jp.proceed(new Object[] { questName });

            // after-returning 어드바이스에 대한 기능을 수행 (proceed를 기준)
            System.out.printf("result : %s", result);
            System.out.printf("%s 퀘스트 수행 완료..\n", questName);

        } catch (Throwable e) {

            // after-throwing 어드바이스에 대한 기능을 수행
            System.out.printf("message : %s\n",e.getMessage());
            System.out.printf("%s 퀘스트 수행 중 에러가 발생..", questName);
        }

        return result;
    }

}
