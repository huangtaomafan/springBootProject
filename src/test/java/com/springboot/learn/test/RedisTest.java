/**
 * CitizenCard.com Inc.
 * Copyright (c) 2004-2017 All Rights Reserved.
 */
package com.springboot.learn.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
 * @author liuzq
 * @version $Id: RedisTest.java, v 0.1 2017年1月11日 上午10:25:13 liuzq Exp $
 */
public class RedisTest {
    //Redis服务器IP
    private static final String ADDR      = "192.168.23.63";

    //Redis的端口号
    private static final int    PORT      = 7001;

    //访问密码
    private static final String AUTH      = null;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int          MAX_IDLE  = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int          MAX_WAIT  = 10000;

    private static int          TIMEOUT   = 10000;

    private static JedisPool    jedisPool = null;

    /**
     * 初始化Redis连接池
     */
    static {
        try {
            JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxIdle(MAX_IDLE);
            jedisPoolConfig.setMaxWaitMillis(MAX_WAIT);
            jedisPoolConfig.setTestOnBorrow(true);
            jedisPool = new JedisPool(jedisPoolConfig, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取Jedis实例
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        //从redis验证验证码
        String mobile = "15821467225";
        String msgType = "07";
        String key = mobile + "_SMS_" + msgType;
        Jedis jedis = getJedis();
        String msgCache = jedis.get(key);
        System.out.println(msgCache);
    }
}
