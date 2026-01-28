package com.yonsai.books.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class LogAspect {

    @Before("execution(* com.yonsai.books.controller..*(..)) || " +
            "execution(* com.yonsai.books.service..*(..))")

    public void before (JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().toShortString();
        System.out.println("[AOP] METHOD : " + methodName);
    }
}
