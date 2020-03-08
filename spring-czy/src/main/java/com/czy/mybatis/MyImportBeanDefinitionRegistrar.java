package com.czy.mybatis;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * spring提供的一个扩展点，实现ImportBeanDefinitionRegistrar接口
 * 可以自定义的类放入spring中的beanDefinitionMap中，不需要通过spring自己扫描类来填充
 * 此时我们可以自己手动设置这个类的信息，把多个mapper接口放入这个类
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(MyFactoryBean.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        /* 此处是写死的，实际上可以根据importingClassMetadata拿到@MyScan注解上的信息，然后for循环来遍历这个路径下所有mapper接口 */
        beanDefinition.getPropertyValues().add("mapperInterface", "com.czy.mapper.UserDao");
        registry.registerBeanDefinition("myFactoryBean", beanDefinition);
    }
}
