package com.hongpro.poortravel.service.posts.service.consumer;

import com.hongpro.poortravel.service.posts.service.consumer.fallback.UserPostcollectServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "poortravel-service-admin", fallback = UserPostcollectServiceFallback.class)
public interface UserPostcollectService {

    @RequestMapping(value = "v1/userpostcollects/list/{userGuid}", method = RequestMethod.GET)
    public String getUserPostcollectList(@PathVariable(value = "userGuid") String userGuid);

}
