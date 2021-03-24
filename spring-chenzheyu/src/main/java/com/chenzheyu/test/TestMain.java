package com.chenzheyu.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chenzheyu.app.AppConfig;
import com.chenzheyu.dao.IndexDao;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean(IndexDao.class).query();
    }
}
