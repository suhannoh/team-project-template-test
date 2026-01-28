package com.yonsai.books.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.stereotype.Component;

@org.aspectj.lang.annotation.Aspect
@Component
public class LogAspect {

    /**
     * 컨트롤러 & 서비스 계층 메서드 실행 전/후에 공통 로그를 출력하는 AOP Advice.
     *
     * @param joinPoint 실행되는 타겟 메서드 정보(메서드명, 파라미터 등)를 담고 있으며 proceed() 호출 시 실제 메서드를 실행합니다.
     * @return 대상 메서드가 반환하는 결과값 그대로 반환합니다.
     * @throws Throwable proceed() 호출 중 발생할 수 있는 모든 예외를 호출자에게 전파합니다.
     */
    @Around("execution(* com.yonsai.books.controller..*(..)) || " +
            "execution(* com.yonsai.books.service..*(..))")
    public Object logStartEnd(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("▶ START : " + joinPoint.getSignature().toShortString());

        Object result = joinPoint.proceed();   // 실제 메서드 실행

        System.out.println("■ END : " + joinPoint.getSignature().toShortString());

        return result;
    }
}
