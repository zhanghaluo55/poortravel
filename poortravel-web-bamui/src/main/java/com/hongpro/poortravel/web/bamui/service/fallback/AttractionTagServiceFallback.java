package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.AttractionTagService;
import org.springframework.stereotype.Component;

@Component
public class AttractionTagServiceFallback implements AttractionTagService {
    @Override
    public String page(int pageNum, int pageSize, String attractionTagJson) {
        return null;
    }

    @Override
    public String get(String attractionTagGuid) {
        return null;
    }

    @Override
    public String save(String attractionTagJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String attractionTagGuid) {
        return null;
    }

    @Override
    public String update(String attractionTagGuid, String attractionTagJson) {
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
