package com.czy.mybatis;

import org.springframework.beans.factory.FactoryBean;

/**
 * 模拟mybatis把生成的代理对象交给spring管理
 * 实现这个接口的类交给spring管理，那么它返回的object也会被交给spring管理
 */
public class MyFactoryBean implements FactoryBean<Object> {
    Class<?> mapperInterface;

    public void setMapperInterface(Class<?> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public Object getObject() throws Exception {
        return MySession.getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterface;
    }
}
