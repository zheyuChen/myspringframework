package com.czy.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class PersonService implements ApplicationContextAware {

    @Autowired
    UserService userService;

    public PersonService() {
        System.out.println("person service");
    }

    public void getService() {
        System.out.println(userService);
    }

    //生命周期初始化回调方法
    @PostConstruct
    public void lifeCycleInit() {
        System.out.println("call person service lifecycle init callback");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("call aware callback");
    }
}
