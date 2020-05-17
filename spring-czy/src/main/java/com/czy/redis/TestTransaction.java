package com.czy.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

public class TestTransaction {
    public static void main(String[] args) {
        // Jedis jedis = new Jedis("127.0.0.1", 6379);
        // Transaction transaction = jedis.multi();
        // // transaction.set("k4", "v4");
        // // transaction.set("k5", "v5");
        // // transaction.exec();
        //
        // transaction.set("k4", "v444");
        // transaction.set("k5", "v555");
        // transaction.discard();
        TestTransaction testTransaction = new TestTransaction();
        boolean b = testTransaction.transMethod();
        System.out.println("main retValue-----:" + b);
    }

    public boolean transMethod() {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        int balance;
        int debt;
        int amtToSubtract = 10;

        jedis.watch("balance");
        balance = Integer.parseInt(jedis.get("balance"));
        if (balance < amtToSubtract) {
            jedis.unwatch();
            System.out.println("modify");
            return false;
        } else {
            System.out.println("-----------transaction");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            System.out.println("---------------" + balance);
            System.out.println("---------------" + debt);
            return true;
        }
    }
}
