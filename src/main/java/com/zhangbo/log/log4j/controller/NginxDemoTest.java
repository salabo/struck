package com.zhangbo.log.log4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
@RestController
public class NginxDemoTest {

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/header")
    public String test(){
        String access_token = request.getHeader("access_token");
        if (access_token == null){
            throw new RuntimeException("没token");
        }
        return access_token;
    }

    @PostMapping("/jusdaTest")
    public String test1(@RequestBody Demo demo){
        return demo.toString();
    }
}
