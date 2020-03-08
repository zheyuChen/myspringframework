package com.czy.mybatis;

import org.apache.ibatis.annotations.Select;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Select annotation = method.getAnnotation(Select.class);
        if (annotation != null) {
            String sql = annotation.value()[0];
            System.out.println(sql);
        }
        return null;
    }
}
