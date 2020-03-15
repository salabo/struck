package com.zhangbo.log.log4j.confiuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
@Configuration
@Slf4j
public class JedisConfig extends CachingConfigurerSupport {


    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.timeout:0}")
    private int timeout;

    @Value("${spring.redis.pool.max-active:200}")
    private int maxActive;

    @Value("${spring.redis.pool.max-idle:8}")
    private int maxIdle;

    @Value("${spring.redis.pool.min-idle:0}")
    private int minIdle;

    @Value("${spring.redis.pool.max-wait:8}")
    private long maxWaitMillis;

    @Value("${a.b:zb82219632}")
    private String pwd;



    @Bean
    public JedisPool redisPoolFactory(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setMinIdle(minIdle);
        JedisPool jedisPool = new JedisPool(jedisPoolConfig,host,port,timeout,pwd);

        log.info("JedisPool注入成功！");
        log.info("redis地址：" + host + ":" + port);
        return  jedisPool;
    }
}
