package com.czy.test;

import com.czy.app.AppConfig;
import com.czy.service.PersonService;
import com.czy.service.Query;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean(PersonService.class).getService();

        Query query = (Query) ac.getBean("q");
        query.query();
        System.out.println("------------------------------");
        query.query1("s1");
        System.out.println("------------------------------");
        query.query2("s1", "s2");
    }
}
