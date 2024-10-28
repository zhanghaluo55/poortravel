package com.hongpro.poortravel.service.attraction.service.consumer.fallback;

import com.hongpro.poortravel.service.attraction.service.consumer.TagService;
import org.springframework.stereotype.Component;

@Component
public class TagServiceFallback implements TagService {

    @Override
    public String get(String tagGuid) {
        return null;
    }

    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String getSome(String attrtaglist) {
        return null;
    }
}
