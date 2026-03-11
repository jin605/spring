package com.beyond.aop.aspect;

import com.beyond.aop.annotation.Repeat;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class WeaponAspect {

    // @Around("execution(* com.beyond.aop.weapon.Sword.attack())")
    // @Around("execution(* com.beyond.aop.weapon.Weapon.attack())")
    // @Around("execution(* com.beyond.aop.weapon.*.attack())")
    // @Around("execution(* com.beyond.aop.weapon.*.attack()) && !bean(windForce)")
    @Around("execution(* com.beyond.aop.weapon.*.attack())" + " && !@annotation(com.beyond.aop.annotation.NoLogging)")
    public String attackAdvice(ProceedingJoinPoint jp) {
        String result = null;

        try {
            System.out.println("공격을 준비 중입니다.");

            result = (String) jp.proceed();
            System.out.println(result);
            System.out.println("공격을 성공했습니다.");

        } catch (Throwable e) {

            System.out.println("에러가 발생 했습니다.");

        }

        return result;

    }

    @Around("@annotation(com.beyond.aop.annotation.Repeat)")
    public String repeatAdvice(ProceedingJoinPoint jp) {
        String result = null;
        MethodSignature signature = (MethodSignature)jp.getSignature();
        Repeat repeat = signature.getMethod().getAnnotation(Repeat.class);

        // System.out.println(repeat);
        // System.out.println(repeat.value());
        // System.out.println(repeat.count());
        // System.out.println();



        try {
            System.out.println("반복 실행 전");
            result = (String) jp.proceed();
            System.out.println("반복 실행 후");

        } catch (Throwable e) {
            System.out.println("에러 발생");
        }

        return result;
    }

}
























