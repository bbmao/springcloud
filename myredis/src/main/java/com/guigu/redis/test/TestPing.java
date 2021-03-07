package com.guigu.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.Set;

/**
 * @auther:mao
 * @create: 2021-03-07 15:37:39
 **/
public class TestPing {

    public static void main(String[] args){
//        Jedis jedis = new Jedis("192.168.0.110", 6379);
//        System.out.println(jedis.ping());
//        jedis.set("k1", "v1");
//        jedis.set("k2", "v2");
//        jedis.set("k3", "v3");
//        System.out.println(jedis.get("k3"));
//        Set<String> keys = jedis.keys("*");
//        for (String key: keys){
//            System.out.println(key + ":" + jedis.get(key) + "\t");
//        }
//        Transaction transaction = jedis.multi();
//        transaction.set("k44", "v44");
//        transaction.set("k54", "v55");
//        transaction.discard();

        TestPing testPing = new TestPing();
        boolean b = testPing.tranMethod();
        System.out.println("b:"+ b);
    }

    public boolean tranMethod(){
        Jedis jedis = new Jedis("192.168.0.110", 6379);
        int balance;
        int debt;
        int amtToSubtract = 10;

        jedis.watch("balance");
        balance = Integer.parseInt(jedis.get("balance"));
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        jedis.set("balance", "5");
        if (balance < amtToSubtract){
            jedis.unwatch();
            System.out.println("modify");
            return false;
        }else {
            System.out.println("**transaction**");
            Transaction transaction = jedis.multi();
            transaction.decrBy("balance", amtToSubtract);
            transaction.incrBy("debt", amtToSubtract);
            transaction.exec();
            balance = Integer.parseInt(jedis.get("balance"));
            debt = Integer.parseInt(jedis.get("debt"));
            System.out.println("balance:"+ balance);
            System.out.println("debt:"+ debt);
            return true;
        }
    }
}
