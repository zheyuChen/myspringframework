package com.czy.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* com.czy.service.PersonService.*(..))") // the pointcut expression
    private void anyOldTransfer() {

    }

    @Before("anyOldTransfer()")
    public void before() {
        System.out.println("------aop---------");
    }
}
