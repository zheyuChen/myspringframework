package com.czy.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.czy.model.Person;

@Controller
public class IndexController {

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
}
