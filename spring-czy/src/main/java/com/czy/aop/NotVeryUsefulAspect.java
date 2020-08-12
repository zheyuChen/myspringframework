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
     * 针对this切点举一下例子，所有方法的返回类型和this切点声明的一致将进行aop处理 有个重要的点，spring aop默认是用的jdk动态代理，所以此处写的是接口，而不是实现类
     * 如果要写实现类，需要使用cglib代理，需要在@EnableAspectJAutoProxy注解里把proxyTargetClass改成true jdk动态代理 x extends proxy implents L
     * cglib代理 x extends Limpl implents L this针对的是实现Query接口的代理对象
     */
    @Pointcut("this(com.czy.service.Query)")
    private void pointCutThis() {

    }

    @Before("pointCutThis()")
    public void beforeQuery() {
        System.out.println("----------query aop before--------------");
    }

    /**
     * target针对的是实现Query接口的目标对象 此处可以写接口，也可以写接口的实现类 如果写接口可以和this共存，先执行this的aop
     */
    /*    @Pointcut("target(com.czy.service.Query)")
    private void pointCutTarget() {
    
    }
    
    @Before("pointCutTarget()")
    public void beforeQueryForTarget() {
        System.out.println("----------query aop for target before--------------");
    }*/

    // /**
    // * @param joinPoint
    // * @@AfterReturning 表示这是一个返回通知，即有目标方法有返回值的时候才会触发，该注解中的 returning
    // 属性表示目标方法返回值的变量名，这个需要和参数一一对应吗，注意：目标方法的返回值类型要和这里方法返回值参数的类型一致，否则拦截不到，如果想拦截所有（包括返回值为 void），则方法返回值参数可以为 Object
    // */
    // @AfterReturning(value = "@annotation(Action)",returning = "r")
    // public void returing(JoinPoint joinPoint,Integer r) {
    // Signature signature = joinPoint.getSignature();
    // String name = signature.getName();
    // System.out.println(name + "方法返回："+r);
    // }
    //
    // /**
    // * 异常通知
    // * @param joinPoint
    // * @param e 目标方法所抛出的异常，注意，这个参数必须是目标方法所抛出的异常或者所抛出的异常的父类，只有这样，才会捕获。如果想拦截所有，参数类型声明为 Exception
    // */
    // @AfterThrowing(value = "@annotation(Action)",throwing = "e")
    // public void afterThrowing(JoinPoint joinPoint,Exception e) {
    // Signature signature = joinPoint.getSignature();
    // String name = signature.getName();
    // System.out.println(name + "方法抛异常了："+e.getMessage());
    // }
    //
    // /**
    // * 环绕通知
    // *
    // * 环绕通知是集大成者，可以用环绕通知实现上面的四个通知，这个方法的核心有点类似于在这里通过反射执行方法
    // * @param pjp
    // * @return 注意这里的返回值类型最好是 Object ，和拦截到的方法相匹配
    // */
    // @Around("@annotation(Action)")
    // public Object around(ProceedingJoinPoint pjp) {
    // Object proceed = null;
    // try {
    // //这个相当于 method.invoke 方法，我们可以在这个方法的前后分别添加日志，就相当于是前置/后置通知
    // proceed = pjp.proceed();
    // } catch (Throwable throwable) {
    // throwable.printStackTrace();
    // }
    // return proceed;
    // }
}
