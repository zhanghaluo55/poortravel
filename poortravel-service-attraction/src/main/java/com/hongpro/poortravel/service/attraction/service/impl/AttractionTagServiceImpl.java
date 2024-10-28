package com.hongpro.poortravel.service.attraction.service.impl;

import com.hongpro.poortravel.common.domain.AttractionTag;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.attraction.mapper.AttractionTagExtentMapper;
import com.hongpro.poortravel.service.attraction.service.AttractionTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AttractionTagServiceImpl extends BaseServiceImpl<AttractionTag, AttractionTagExtentMapper> implements AttractionTagService<AttractionTag> {
    @Autowired
    private AttractionTagExtentMapper attractionTagExtentMapper;

    @Override
    public List<AttractionTag> selectListAttrTags(String aid) {
        AttractionTag attractionTag=new AttractionTag();
        attractionTag.setAid(Integer.valueOf(aid));
        return attractionTagExtentMapper.select(attractionTag);
    }
}
