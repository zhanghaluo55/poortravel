package com.hongpro.poortravel.service.posts.service.consumer.fallback;

import com.hongpro.poortravel.service.posts.service.consumer.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {


    @Override
    public String page(int pageNum, int pageSize, String userJson) {
        return null;
    }

    @Override
    public String get(String userGuid) {
        return null;
    }

    @Override
    public String save(String userJson) {
        return null;
    }

    @Override
    public String delete(String userGuid) {
        return null;
    }

    @Override
    public String update(String userGuid, String userJson) {
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
