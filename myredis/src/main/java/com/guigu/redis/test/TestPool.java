package com.guigu.redis.test;

import com.guigu.redis.test.util.JedisPoolUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisSentinelPool;

/**
 * @auther:mao
 * @create: 2021-03-07 17:52:29
 **/
public class TestPool {

    public static void main(String[] args){
        JedisSentinelPool jedisPool = JedisPoolUtil.getJedisPoolInstance();
//        JedisPool jedisPool2 = JedisPoolUtil.getJedisPoolInstance();
//        System.out.println(jedisPool == jedisPool2);
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set("aa", "bb");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            JedisPoolUtil.release(jedisPool, jedis);
        }
    }
}
