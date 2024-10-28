package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.AttractionService;
import org.springframework.stereotype.Component;

@Component
public class AttractionServiceFallback implements AttractionService {
    @Override
    public String page(int pageNum, int pageSize, String attractionJson) {
        return null;
    }

    @Override
    public String getOne(String attractionGuid) {
        return null;
    }


    @Override
    public String save(String attractionJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String attractionGuid) {
        return null;
    }

    @Override
    public String update(String attractionGuid, String attractionJson) {
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
