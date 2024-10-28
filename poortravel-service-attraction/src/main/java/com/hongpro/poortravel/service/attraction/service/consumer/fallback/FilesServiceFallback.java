package com.hongpro.poortravel.service.attraction.service.consumer.fallback;

import com.hongpro.poortravel.service.attraction.service.consumer.FilesService;
import org.springframework.stereotype.Component;

@Component
public class FilesServiceFallback implements FilesService {

    @Override
    public String get(String filesGuid) {
        return null;
    }

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String getSome(String attrfileslist) {
        return null;
    }
}
