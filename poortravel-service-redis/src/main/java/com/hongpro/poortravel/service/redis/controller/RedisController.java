package com.hongpro.poortravel.service.redis.controller;

import com.hongpro.poortravel.service.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {
    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(String key, String value, long seconds) {
        System.out.println("key:" + key);
        System.out.println("value" + value);
        Object o = redisService.put(key, value, seconds);
        return "ok";
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public Object get(String key) {
        Object o = redisService.get(key);
        if (o != null) {
            String json = String.valueOf(o);
            return json;
        }
        return null;
    }
}
