package com.czy.redis;

import redis.clients.jedis.Jedis;

public class TestPing {
    public static void main(String[] args) {
        // Jedis jedis = new Jedis("127.0.0.1", 6379);
        /* 连接远程redis，这个需要在远程redis服务配置里去掉bind本地地址和端口，并且关闭保护模式
        * 实际生产环境中会bind自己的应用服务器地址 */
        Jedis jedis = new Jedis("123.56.139.134", 6379);
        System.out.println(jedis.ping());
    }
}
