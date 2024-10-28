package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.PostsService;
import org.springframework.stereotype.Component;

@Component
public class PostsServiceFallback implements PostsService {
    @Override
    public String page(int pageNum, int pageSize, String postsJson) {
        return null;
    }

    @Override
    public String get(String postGuid) {
        return null;
    }

    @Override
    public String save(String postsJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String postGuid) {
        return null;
    }

    @Override
    public String update(String postGuid, String postJson) {
        return null;
    }


    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String search(String key) {
        return null;
    }

}
