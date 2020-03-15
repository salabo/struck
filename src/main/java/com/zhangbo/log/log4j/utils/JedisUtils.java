package com.zhangbo.log.log4j.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
@Component
public class JedisUtils {

    @Autowired
    private JedisPool jedisPool;


    private Jedis getJedis() {
            return  jedisPool.getResource();
    }

    public long publish (String channel,String message){
        Long publish = 0L;
        try {
            Jedis jedis = getJedis();
            publish   = jedis.publish(channel, message);
        } catch (Exception e){

        }finally {
            jedisPool.getResource().close();
        }
        return publish;

    }

    public long rpublish (String channel,String message){
        Jedis jedis = getJedis();
        Long rpush = jedis.rpush(channel, message);
        return rpush;
    }
}
