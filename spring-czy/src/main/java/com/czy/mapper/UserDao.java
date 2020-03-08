package com.czy.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * mybatis中定义的mapper接口
 * 靠AppConfig类中的@MapperScan("com.czy.mapper")扫描到，然后注入spring容器，然后才能在service中调用
 * mybatis中核心原理之一：为什么此处是一个接口，但是拿到userdao后可以执行list，然后返回一个查询结果？
 * 执行list()方法返回的有结果，说明一个现象，这个接口被实例化了，我们拿到的是一个对象
 * 那么此处我们可以想到，提供一个接口，然后返回一个实现此接口的对象，用到的基本上是jdk动态代理技术
 * 熟悉源码的话我们可以知道在MapperProxyFactory类中
 * return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface }, mapperProxy);
 * 那么到目前为止，还有一个疑问，那就是spring扫描的是一个接口，mybatis怎么来把一个实现此接口的代理对象放到spring容器中
 * 换句话说，spring和mybatis在此处的结合点是什么？
 * 用的是factoryBean，实现这个接口的类会重写里面的三个方法，其中getObject返回的对象就是
 * 我们需要的代理对象，如果实现这个接口的类交给spring管理，那么它返回的object也会被交给spring管理
 * 那么问题来了，如何把这个类交给spring管理，常用的注解@Component，此时不行，因为如果用注解，怎么给上面的类设置mapper接口属性
 * 那么用xml，虽然可行，但是存在一个问题，每次只能把一个mapper接口放到上面的factoryBean实现类里，那mybatis如何做到把多个mapper
 * 接口放入上面的factoryBean实现类里的呢？
 * 其实这是利用了spring提供给外面的一个扩展点ImportBeanDefinitionRegistrar，实现ImportBeanDefinitionRegistrar接口
 * 可以把自定义的类放入spring中的beanDefinitionMap中，不需要通过spring自己扫描类来填充
 * 此时我们可以自己手动设置这个类的信息，把多个mapper接口放入这个类
 * mybatis靠这个来整个两个框架，这也是为啥需要mybaits-spring这个依赖的原因
 */
public interface UserDao {

    @Select("select * from user")
    public List<Map<String, Object>> list();
}
