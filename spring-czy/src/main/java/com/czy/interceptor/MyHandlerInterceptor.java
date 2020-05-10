package com.czy.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerInterceptor implements HandlerInterceptor {
    /* 该方法在目标方法之前被调用
    * 若返回true，则继续调用后续的拦截器和目标方法
    * 若返回false，则不会再调用后续的拦截器和目标方法
    * 可以考虑做权限、日志、事务等 */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {
        System.out.println("MyHandlerInterceptor preHandle");
        return true;
    }

    /* 该方法在目标方法之后，渲染视图方法之前调用
    * 可以对请求域中的属性或视图做出修改 */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
        ModelAndView modelAndView) throws Exception {
        System.out.println("MyHandlerInterceptor postHandle");
    }

    /* 该方法在渲染视图方法之后调用
    * 释放资源 */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {
        System.out.println("MyHandlerInterceptor afterCompletion");
    }
}
