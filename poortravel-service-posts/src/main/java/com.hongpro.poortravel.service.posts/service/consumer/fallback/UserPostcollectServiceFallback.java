package com.hongpro.poortravel.service.posts.service.consumer.fallback;

import com.hongpro.poortravel.service.posts.service.consumer.UserPostcollectService;
import org.springframework.stereotype.Component;

@Component
public class UserPostcollectServiceFallback implements UserPostcollectService {

    @Override
    public String getUserPostcollectList(String userGuid) {
        return null;
    }
}
