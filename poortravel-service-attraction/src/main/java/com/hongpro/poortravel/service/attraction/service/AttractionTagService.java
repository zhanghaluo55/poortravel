package com.hongpro.poortravel.service.attraction.service;

import com.hongpro.poortravel.common.domain.AttractionTag;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface AttractionTagService<T> extends BaseService<T> {
    public List<AttractionTag> selectListAttrTags(String aid);
}
