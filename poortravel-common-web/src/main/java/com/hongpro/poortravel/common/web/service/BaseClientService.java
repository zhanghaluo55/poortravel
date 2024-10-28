package com.hongpro.poortravel.common.web.service;

import com.hongpro.poortravel.common.hystrix.Fallback;

/**
 * 通用服务消费者
 */
public interface BaseClientService {
    default String page(int pageNum, int pageSize, String domainJson){
        return Fallback.badGateway();
    }
}
