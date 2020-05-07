package com.czy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import com.czy.model.Person;

@Controller
public class IndexController {

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
    public String addPerson(Person person) {
        System.out.println("add person: " + person);
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
