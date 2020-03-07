package com.czy.controller;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * 模拟spring boot中内嵌tomcat直接启动web项目
 */
public class SpringApplication {
    public static void run() {
        Tomcat tom = new Tomcat();
        tom.setPort(8989);

        try {
            /* 来判断执行web项目，等价于将项目放到tomcat的webapp目录下 */
            tom.addWebapp("/", "D:\\Download");

            //tom.addContext("/", "D:\\Download");
            tom.start();
            tom.getServer().await();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
    }
}
