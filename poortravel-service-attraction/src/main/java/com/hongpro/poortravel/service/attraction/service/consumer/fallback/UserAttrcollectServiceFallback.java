package com.hongpro.poortravel.service.attraction.service.consumer.fallback;

import com.hongpro.poortravel.service.attraction.service.consumer.UserAttrcollectService;
import org.springframework.stereotype.Component;

@Component
public class UserAttrcollectServiceFallback implements UserAttrcollectService {

    @Override
    public String getUserAttrcollectList(String userGuid) {
        return null;
    }
}
