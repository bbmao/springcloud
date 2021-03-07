package com.guigu.redis.test.util;

import redis.clients.jedis.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @auther:mao
 * @create: 2021-03-07 17:45:07
 **/
public class JedisPoolUtil {

    private static volatile JedisSentinelPool jedisPool = null;

    private JedisPoolUtil(){
    }

    public static JedisSentinelPool getJedisPoolInstance(){
        if (jedisPool == null){
            synchronized (JedisPoolUtil.class){
                if (jedisPool == null){
                    JedisPoolConfig poolConfig = new JedisPoolConfig();
                    poolConfig.setMaxTotal(1000);
                    poolConfig.setMaxIdle(32);
                    poolConfig.setMaxWaitMillis(100*1000);
                    poolConfig.setTestOnBorrow(true);

                    Set<String> sentinelsc = new HashSet<>();
                    sentinelsc.add("192.168.0.110:6379");
                    sentinelsc.add("192.168.0.110:6380");
                    sentinelsc.add("192.168.0.110:6381");

                    jedisPool = new JedisSentinelPool("host6379", sentinelsc, poolConfig);
                }
            }
        }
        return jedisPool;
    }

    public static void release(JedisSentinelPool jedisPool, Jedis jedis){
        if (jedis != null){
            jedisPool.returnResourceObject(jedis);
        }
    }
}
