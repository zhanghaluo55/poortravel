package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.web.bamui.service.fallback.RedisServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-redis", fallback = RedisServiceFallback.class)
public interface RedisService {
    @RequestMapping(value = "put", method = RequestMethod.POST)
    public String put(@RequestParam(value = "key") String key, @RequestParam(value = "value") String value, @RequestParam(value = "seconds") long seconds);

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "key") String key);

}
