package com.czy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.czy.model.Person;

import java.util.Date;

@Controller
public class IndexController {
    @ResponseBody
    @RequestMapping(value = "/testHttpMessageConverter", method = RequestMethod.POST)
    public String testHttpMessageConverter(@RequestBody String body) {
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
