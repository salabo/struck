package com.zhangbo.log.log4j.controller;

import com.zhangbo.log.log4j.utils.JedisUtils;
import com.zhangbo.log.log4j.utils.RedisUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
@RestController
@RequestMapping("/log")
public class Log4JTestController {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private JedisUtils jedisUtils;

    private static  final  String KEY = "logstash-";

    final  static Logger logger = LoggerFactory.getLogger(Log4JTestController.class);
    @GetMapping("/createLog")
    public String log4jDemo(int param){
        int a =10;
        try {
            a = a/param;
        }catch (Exception e){
            logger.error("--------");
        }
        return a+"";
    }

    @GetMapping("/createValue")
    public String redis4Logstash(@RequestParam("value") String value){
        redisUtils.set(KEY+value,value);
        logger.info("-----插入数据{},key为{}",value,KEY+value);
        return "success";
    }

    @GetMapping("/publish")
    public String publish(String key,String message){
        return jedisUtils.publish(KEY + System.currentTimeMillis(), message)+"";
    }

    @GetMapping("/rpublish")
    public String rpublish(String message){
        return jedisUtils.rpublish("logstash-list", message)+"";
    }

    @GetMapping("/push")
    public String pushList(String val){
        List<Object> list = new ArrayList<>();
        list.add(val);
        long l = redisUtils.lPushAll("logstash-list-test", list);
        return l+"";
    }

}
