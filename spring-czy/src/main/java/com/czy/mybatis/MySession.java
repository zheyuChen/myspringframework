package com.czy.mybatis;

import java.lang.reflect.Proxy;

/**
 * 既然知道mybatis靠jdk动态代理实现，那么此处模拟mybatis实现
 */
public class MySession {

    public static Object getMapper(Class<?> clazz) {
        return Proxy.newProxyInstance(MySession.class.getClassLoader(), new Class<?>[]{clazz}, new MyInvocationHandler());
    }
}
