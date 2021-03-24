package com.chenzheyu.dao;

import org.springframework.stereotype.Component;

@Component
public class IndexDaoImpl implements IndexDao {
    @Override
    public void query() {
        System.out.println("index dao query.");
    }
}
