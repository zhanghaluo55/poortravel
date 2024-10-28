package com.hongpro.poortravel.service.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Object put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }


}
