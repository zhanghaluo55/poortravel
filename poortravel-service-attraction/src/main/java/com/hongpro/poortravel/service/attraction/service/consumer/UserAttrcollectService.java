package com.hongpro.poortravel.service.attraction.service.consumer;

import com.hongpro.poortravel.service.attraction.service.consumer.fallback.UserAttrcollectServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "poortravel-service-admin", fallback = UserAttrcollectServiceFallback.class)
public interface UserAttrcollectService {

    @RequestMapping(value = "v1/userattrcollects/list/{userGuid}", method = RequestMethod.GET)
    public String getUserAttrcollectList(@PathVariable(value = "userGuid") String userGuid);

}
