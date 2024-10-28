package com.hongpro.poortravel.web.bamui.service.fallback;

import com.hongpro.poortravel.common.hystrix.Fallback;
import com.hongpro.poortravel.web.bamui.service.FilesService;
import org.springframework.stereotype.Component;

@Component
public class FilesServiceFallback implements FilesService {
    @Override
    public String page(int pageNum, int pageSize, String fileJson) {
        return null;
    }

    @Override
    public String get(String fileGuid) {
        return null;
    }

    @Override
    public String save(String fileJson) {
        return Fallback.badGateway();
    }

    @Override
    public String delete(String fileGuid) {
        return null;
    }

    @Override
    public String update(String fileGuid, String fileJson) {
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
