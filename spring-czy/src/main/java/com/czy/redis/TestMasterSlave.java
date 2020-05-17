package com.czy.redis;

import redis.clients.jedis.Jedis;

public class TestMasterSlave {
    public static void main(String[] args) {
        Jedis jedisMaster = new Jedis("127.0.0.1", 6379);
        Jedis jedisSlave = new Jedis("127.0.0.1", 6380);
        jedisSlave.slaveof("127.0.0.1", 6379);
        jedisMaster.set("ccc", "111");
        String ccc = jedisSlave.get("ccc");
        System.out.println(ccc);
    }
}
