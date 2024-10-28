package com.hongpro.poortravel.web.posts.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.posts.service.PostsService;
import org.springframework.stereotype.Component;

@Component
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String postsJson) {
        return null;
    }

    @Override
    public String get(int postGuid) {
        return null;
    }

    @Override
    public String save(String postsJson) {
        return Fallback.badGateway();
    }

}
