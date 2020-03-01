package com.czy.ext;

import com.czy.service.IndexService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        GenericBeanDefinition userService = (GenericBeanDefinition) beanFactory.getBeanDefinition("userService");
        System.out.println("userService mode = " + userService.getAutowireMode());

        DefaultListableBeanFactory def = (DefaultListableBeanFactory) beanFactory;
        GenericBeanDefinition index = new GenericBeanDefinition();
        index.setBeanClass(IndexService.class);
        def.registerBeanDefinition("indexService", index);
    }
}
