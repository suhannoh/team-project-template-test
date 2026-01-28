package com.yonsai.books.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class LogAspect {

    //  메서드 실행 전
//    @Before("execution(* com.yonsai.books.controller..*(..)) || " +
//            "execution(* com.yonsai.books.service..*(..))")
//
//    public void before (JoinPoint joinPoint) {
//        String methodName = joinPoint.getSignature().toShortString();
//        System.out.println("[AOP] METHOD : " + methodName);
//    }

    @Around("execution(* com.yonsai.books.controller..*(..)) || " +
            "execution(* com.yonsai.books.service..*(..))")
    public Object logStartEnd(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("▶ START : " + joinPoint.getSignature().toShortString());

        Object result = joinPoint.proceed();   // 실제 메서드 실행

        System.out.println("■ END : " + joinPoint.getSignature().toShortString());

        return result;
    }
}
