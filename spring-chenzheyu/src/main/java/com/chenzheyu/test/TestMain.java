package com.chenzheyu.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.chenzheyu.app.AppConfig;
import com.chenzheyu.dao.IndexDao;
import com.chenzheyu.dao.IndexDaoImportSelector;

public class TestMain {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        ac.getBean(IndexDao.class).query();
        ac.getBean(IndexDaoImportSelector.class).query();
    }
}
