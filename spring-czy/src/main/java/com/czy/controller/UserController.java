package com.czy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @GetMapping("/user")
    @ResponseBody
    public Map<String, String> user() {
        System.out.println("-------------user controller----------");
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        return map;
    }

    @GetMapping("/user/hello")
    public String userHello() {
        System.out.println("-------------user hello----------");
        return "hello";
    }

    @ResponseBody
    @PostMapping("/user")
    public String addUser() {
        System.out.println("add user");
        return "add user success";
    }

    @ResponseBody
    @PutMapping("/user")
    public String updateUser() {
        System.out.println("update user");
        return "update user success";
    }
}
