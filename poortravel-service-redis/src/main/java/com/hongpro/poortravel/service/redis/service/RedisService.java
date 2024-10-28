package com.hongpro.poortravel.service.redis.service;

public interface RedisService {
    /**
     * @param key
     * @param value
     * @param seconds 超过时间
     */
    public Object put(String key, Object value, long seconds);

    public Object get(String key);
}
