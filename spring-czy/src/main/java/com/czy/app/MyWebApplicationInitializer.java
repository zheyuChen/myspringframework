package com.czy.app;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 替代了传统的springmvc配置文件 1.web.xml（tomcat启动时加载） contextloadlistener,传入applicationContext.xml 初始化化spring环境；注册一个servet拦截请求
 * 2.applicationContext.xml 扫描业务类 3.springmvc.xml 扫描controller javaconfig技术实现依赖于servlet3.0以后新增的规范，SPI机制 springweb
 * resources/META-INF/services/javax.servlet.ServletContainerInitializer中提供了
 * 实现ServletContainerInitializer接口的SpringServletContainerInitializer类，那么项目放到tomcat容器中
 * 容器启动后就会调用onStartup()方法，就相当于启动时加载web.xml 那么为啥实现的是WebApplicationInitializer接口，但是也可以执行到onStartup方法呢？
 * 主要依赖于另一个规范，实现ServletContainerInitializer接口的类上加上
 * 
 * @HandlesTypes(WebApplicationInitializer.class) 这个注解，也就是SpringServletContainerInitializer，那么在执行这个类中的onStartup方法，会传入所有
 *                                                实现WebApplicationInitializer接口的类的集合---webAppInitializerClasses
 */
public class MyWebApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // Load Spring web application configuration
        /* 替代了web.xml中的初始化spring环境作用 */
        AnnotationConfigWebApplicationContext ac = new AnnotationConfigWebApplicationContext();
        // AppConfig类中的@ComponentScan("com.czy")替代了applicationContext.xml的扫描业务类作用
        // AppConfig类中的@ComponentScan("com.czy")同时也替代了pringmvc.xml的扫描controller作用
        ac.register(AppConfig.class);
        // ac.refresh();

        // Create and register the DispatcherServlet
        /* 替代了web.xml中的注册一个servlet拦截请求作用 */
        DispatcherServlet servlet = new DispatcherServlet(ac);
        ServletRegistration.Dynamic registration = servletContext.addServlet("app", servlet);
        registration.setMultipartConfig(new MultipartConfigElement(""));
        registration.setLoadOnStartup(1);
        registration.addMapping("/*");
    }
}