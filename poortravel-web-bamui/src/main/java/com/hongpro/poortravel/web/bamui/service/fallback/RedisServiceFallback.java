package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.web.bamui.service.RedisService;
import org.springframework.stereotype.Component;

@Component
public class RedisServiceFallback implements RedisService {

    @Override
    public String put(String key, String value, long seconds) {
        return null;
    }

    @Override
    public String get(String key) {
        return null;
    }
}