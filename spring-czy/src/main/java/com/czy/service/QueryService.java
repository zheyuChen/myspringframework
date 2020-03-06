package com.czy.service;

import org.springframework.stereotype.Component;

@Component("q")
public class QueryService implements Query {

    @Override
    public void query() {
        System.out.println("exe query()");
    }

    @Override
    public void query1(String s1) {
        System.out.println("exe query1()");
    }

    @Override
    public void query2(String s1, String s2) {
        System.out.println("exe query2()");
    }
}
