package com.czy.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.czy.model.Person;

@Controller
public class ThymeleafController {
    @RequestMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @RequestMapping(value = "/testConversionServiceConverter", method = RequestMethod.POST)
    public String testConverter(@RequestParam("person") Person person) {
        System.out.println("save person: " + person);
        return "success";
    }

    @RequestMapping("/helloView")
    public String helloView() {
        return "helloView";
    }

    @RequestMapping("/success")
    public String success() {
        return "success";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /* 有@ModelAttribute标记的方法，会在每个目标方法执行之前被springmvc调用 */
    @ModelAttribute
    public void getPerson(@RequestParam(value = "id", required = false) Integer id, Map<String, Object> map) {
        if (id != null) {
            Person person = new Person(1, "Tom", "123456", "12", "Tom@qq.com");
            System.out.println("模拟从数据库获取person:" + person);
            map.put("person", person);
        }
    }

    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(Person person) {
        System.out.println("修改: " + person);
        return "success";
    }
}
