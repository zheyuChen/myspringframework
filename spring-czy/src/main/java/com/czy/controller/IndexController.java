package com.czy.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.czy.model.Person;

@Controller
public class IndexController {

    @RequestMapping("/testSimpleMappingExceptionResolver")
    public String testSimpleMappingExceptionResolver(@RequestParam("i") int i) {
        String[] vals = new String[10];
        System.out.println(vals[i]);
        return "success";
    }

    @RequestMapping("/testExceptionHandlerExceptionResolver")
    public String testExceptionHandlerExceptionResolver(@RequestParam("i") int i) {
        System.out.println("result: " + (10 / i));
        return "success";
    }

    @RequestMapping("/testFileUpload")
    public String testFileUpload(@RequestParam("desc") String desc, @RequestParam("file") MultipartFile file)
        throws IOException {
        System.out.println("desc: " + desc);
        System.out.println("originalFileName: " + file.getOriginalFilename());
        System.out.println("inputSteam: " + file.getInputStream());
        return "success";
    }

    /* 测试国际化 */
    @Autowired
    private ResourceBundleMessageSource messageSource;

    @RequestMapping("/i18n")
    public String testI18n(Locale locale) {
        String v = messageSource.getMessage("login.username", null, locale);
        System.out.println(v);
        return "success";
    }

    @RequestMapping("/testResponseEntity")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        ServletContext servletContext = session.getServletContext();
        /* 目前没解决把这个文件转为流，in=null */
        InputStream in = servletContext.getResourceAsStream("/static/test.txt");
        byte[] body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=test.txt");

        HttpStatus status = HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<>(body, headers, status);
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/testHttpMessageConverter", method = RequestMethod.POST)
    public String testHttpMessageConverter(@RequestBody String body) {
        /* 目前没解决把上传的文件转成string，试了几个转换器都没成功 */
        System.out.println("body: " + body);
        return "helloworld!" + new Date();
    }

    @RequestMapping(value = "/testConversionServiceConverter", method = RequestMethod.POST)
    public String testConverter(@RequestParam("person") Person person) {
        System.out.println("save person: " + person);
        return "success";
    }

    /* 参与表单对象与后台实体对象属性之间的绑定赋值，下面例子中person对象email属性就不会被赋值，为null */
    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("email");
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public String addPerson(@Validated Person person, BindingResult result) {
        System.out.println("add person: " + person);
        System.out.println("occur error: " + result);
        return "success";
    }

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        return "Index Page";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World";
    }

    @RequestMapping("/helloHtml")
    public String helloHtml(Model model) {
        model.addAttribute("name", "czy");
        model.addAttribute("password", "123456");
        model.addAttribute("sex", "1");
        return "hello";
    }
}
