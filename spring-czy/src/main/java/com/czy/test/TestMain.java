package com.czy.test;

import com.czy.app.AppConfig;
import com.czy.mapper.UserDao;
import com.czy.mybatis.MySession;
import com.czy.service.PersonService;
import com.czy.service.Query;
import com.czy.service.UserGetService;
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

        System.out.println("------------spring整合mybatis获取数据--------------");
        ac.getBean(UserGetService.class).list();

        System.out.println("------------模拟mybatis------------------");
        UserDao userDao = (UserDao) MySession.getMapper(UserDao.class);
        userDao.list();

        System.out.println("------------模拟mybatis把生成的代理对象交给spring管理--------------");
        System.out.println("------------注意用自定义的@MyScan注解--------------");
        ac.getBean(UserGetService.class).list();
    }
}
