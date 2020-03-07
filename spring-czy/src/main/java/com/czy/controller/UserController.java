package com.czy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @RequestMapping("/user")
    @ResponseBody
    public Map<String, String> user() {
        System.out.println("-------------user controller----------");
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }
}
