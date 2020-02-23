package com.czy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonService {

    @Autowired
    UserService userService;

    public PersonService() {
        System.out.println("person service");
    }

    public void getService() {
        System.out.println(userService);
    }
}
