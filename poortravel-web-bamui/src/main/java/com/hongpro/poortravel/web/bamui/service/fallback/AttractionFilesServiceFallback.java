package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.AttractionFilesService;
import org.springframework.stereotype.Component;

@Component
public class AttractionFilesServiceFallback implements AttractionFilesService {
    @Override
    public String page(int pageNum, int pageSize, String attractionFilesJson) {
        return null;
    }

    @Override
    public String get(String attractionFilesGuid) {
        return null;
    }

    @Override
    public String save(String attractionFilesJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String attractionFilesGuid) {
        return null;
    }

    @Override
    public String update(String attractionFilesGuid, String attractionFilesJson) {
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
