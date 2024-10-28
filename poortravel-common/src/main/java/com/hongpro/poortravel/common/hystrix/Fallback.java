package com.hongpro.poortravel.common.hystrix;

import com.google.common.collect.Lists;
import com.hongpro.poortravel.common.constants.HttpStatusConstants;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;

/**
 * 通用熔断方法
 *
 * @author: Rainer
 * @description: TODO
 * @date: 2020/1/27 18:38
 * @return
 */
public class Fallback {
    public static String badGateway() {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()), HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
