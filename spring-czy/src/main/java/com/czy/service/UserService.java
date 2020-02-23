package com.czy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {

    @Autowired
    PersonService personService;

    public UserService() {
        System.out.println("user service");
    }
}
