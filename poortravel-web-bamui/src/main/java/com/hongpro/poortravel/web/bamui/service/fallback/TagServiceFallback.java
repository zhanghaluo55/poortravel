package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.TagService;
import org.springframework.stereotype.Component;

@Component
public class TagServiceFallback implements TagService {
    @Override
    public String page(int pageNum, int pageSize, String tagJson) {
        return null;
    }

    @Override
    public String get(String tagGuid) {
        return null;
    }

    @Override
    public String save(String tagJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String tagGuid) {
        return null;
    }

    @Override
    public String update(String tagGuid, String tagJson) {
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
