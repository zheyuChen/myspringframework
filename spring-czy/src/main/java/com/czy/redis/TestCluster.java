package com.czy.redis;

import java.util.HashSet;
import java.util.Set;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class TestCluster {
    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("123.56.139.134", 6379));
        /* redis集群可以传入一个set集合，里面包含所有节点的信息，但是也可以传一个，它会自动重定向到数据所在槽的节点主机上 */
        JedisCluster jedisCluster = new JedisCluster(nodes);

        jedisCluster.set("username", "admin");
        String username = jedisCluster.get("username");
        System.out.println(username);
        jedisCluster.close();
    }
}
