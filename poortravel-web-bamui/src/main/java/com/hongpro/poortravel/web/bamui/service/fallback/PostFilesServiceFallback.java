package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.PostFilesService;
import org.springframework.stereotype.Component;

@Component
public class PostFilesServiceFallback implements PostFilesService {
    @Override
    public String page(int pageNum, int pageSize, String postFilesJson) {
        return null;
    }

    @Override
    public String get(String postFilesGuid) {
        return null;
    }

    @Override
    public String save(String postFilesJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String postFilesGuid) {
        return null;
    }

    @Override
    public String update(String postFilesGuid, String postFilesJson) {
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
