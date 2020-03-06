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

    /**
     * 针对this切点举一下例子，所有方法的返回类型和this切点声明的一致将进行aop处理
     * 有个重要的点，spring aop默认是用的jdk动态代理，所以此处写的是接口，而不是实现类
     * 如果要写实现类，需要使用cglib代理，需要在@EnableAspectJAutoProxy注解里把proxyTargetClass改成true
     * jdk动态代理 x extends proxy implents L
     * cglib代理  x extends Limpl implents L
     * this针对的是实现Query接口的代理对象
     */
    @Pointcut("this(com.czy.service.Query)")
    private void pointCutThis() {

    }

    @Before("pointCutThis()")
    public void beforeQuery() {
        System.out.println("----------query aop before--------------");
    }

    /**
     * target针对的是实现Query接口的目标对象
     * 此处可以写接口，也可以写接口的实现类
     * 如果写接口可以和this共存，先执行this的aop
     */
/*    @Pointcut("target(com.czy.service.Query)")
    private void pointCutTarget() {

    }

    @Before("pointCutTarget()")
    public void beforeQueryForTarget() {
        System.out.println("----------query aop for target before--------------");
    }*/
}
