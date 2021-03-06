package com.czy.app;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.czy.converter.MyConversionServiceConverter;
import com.czy.interceptor.MyHandlerInterceptor;

@ComponentScan("com.czy")
@EnableAspectJAutoProxy()
// ??????web????????????@EnableWebMvc?????????DelegatingWebMvcConfiguration???????????????????????????web?????????bean?????????????????????????????????????????????????????????????????????
@EnableWebMvc
@MapperScan("com.czy.mapper")
// @MyScan("com.czy.mapper") ????????????mybatis??????
public class AppConfig implements WebMvcConfigurer {

    /* ------------------------??????????????????---------------------- */
    /* ???????????????????????????????????????????????????????????????????????????????????????extendHandlerExceptionResolvers????????????????????????????????????????????????????????? */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {

    }

    /* ??????springmvc???????????????????????????????????? */
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
        Properties properties = new Properties();
        properties.setProperty("java.lang.ArrayIndexOutOfBoundsException", "error");
        simpleMappingExceptionResolver.setExceptionMappings(properties);
        return simpleMappingExceptionResolver;
    }
    /* -------------------------------------------------------- */

    /* ----------------??????????????????????????????-------------- */
    @Autowired
    MyConversionServiceConverter myConversionServiceConverter;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(myConversionServiceConverter);
    }
    /* ----------------------------------------------- */

    /* ----------------???????????????????????????????????????????????????beanName????????????-------------- */
    @Bean
    public BeanNameViewResolver beanNameViewResolver() {
        BeanNameViewResolver beanNameViewResolver = new BeanNameViewResolver();
        beanNameViewResolver.setOrder(100);
        return beanNameViewResolver;
    }
    /* ---------------------------------------------------------------------------- */

    /* ---??????webmvc.adoc???????????????????????????????????????????????????????????????????????????controller??????---- */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("/public", "classpath:/static/")
            .setCachePeriod(31556926);
    }
    /* ------------------------------------------------ */

    /* ----------------?????????????????????------------------- */
    /* ?????????????????????????????? */
    // @Bean
    // public LocaleResolver localeResolver() {
    // return new MyLocaleResolver();
    // }

    /* ??????springmvc??????????????????????????????????????????locale???????????????????????????????????????session???
    * ???????????????????????????????????????LocaleChangeInterceptor????????? */
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());

        /* ???????????????????????? */
        registry.addInterceptor(new MyHandlerInterceptor());
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n.login");
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFallbackToSystemLocale(true);
        return messageSource;
    }
    /* ----------------------------------------------- */

    /* ----------------?????????????????????(fastjson)--------------- */
    /* ????????????????????????????????????????????????????????????????????? */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new FastJsonHttpMessageConverter());

        /* ?????????HttpMessageConverter */
        // converters.add(new StringHttpMessageConverter());
        // converters.add(new ByteArrayHttpMessageConverter());
        // converters.add(new ResourceHttpMessageConverter());
        // converters.add(new SourceHttpMessageConverter<>());
        // converters.add(new AllEncompassingFormHttpMessageConverter());
        // converters.add(new Jaxb2RootElementHttpMessageConverter());
        // converters.add(new MappingJackson2HttpMessageConverter());
    }

    /* ????????????????????????????????????????????????????????????????????? */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /*---------------------------------------------------------- */

    /* ---------------??????servlet3.0??????????????????????????????----------------- */
    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }
    /*-----------------------------------------------*/

    /* -----------------------??????thymeleaf???????????????-------------------- */
    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ITemplateResolver templateResolver;

    @Autowired
    ISpringTemplateEngine springTemplateEngine;

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(webApplicationContext);
        templateResolver.setPrefix("classpath:/thymeleaf/");
        templateResolver.setSuffix(".html");
        // HTML is the default value, added here for the sake of clarity.
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // Template cache is true by default. Set to false if you want
        // templates to be automatically updated when modified.
        templateResolver.setCacheable(true);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        // Enabling the SpringEL compiler with Spring 4.2.4 or newer can
        // speed up execution in most scenarios, but might be incompatible
        // with specific cases when expressions in one template are reused
        // across different data types, so this flag is "false" by default
        // for safer backwards compatibility.
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setTemplateEngine(springTemplateEngine);
        return viewResolver;
    }
    /* ------------------------------------------------------------------ */

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        return factoryBean.getObject();
    }

    public DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/test");
        driverManagerDataSource.setUsername("root");
        driverManagerDataSource.setPassword("123456");
        return driverManagerDataSource;
    }
}
