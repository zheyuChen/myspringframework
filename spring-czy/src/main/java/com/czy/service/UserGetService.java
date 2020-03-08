package com.czy.service;

import com.czy.mapper.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserGetService {

    @Autowired
    UserDao userDao;

    public void list() {
        System.out.println(userDao.list());
    }
}
