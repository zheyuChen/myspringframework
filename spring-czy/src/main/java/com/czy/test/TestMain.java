package com.czy.test;

import com.czy.app.AppConfig;
import com.czy.service.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean(PersonService.class).getService();
    }
}
