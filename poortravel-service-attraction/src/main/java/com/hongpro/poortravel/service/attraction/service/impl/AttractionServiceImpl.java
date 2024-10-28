package com.hongpro.poortravel.service.attraction.service.impl;

import com.hongpro.poortravel.common.domain.Attraction;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.attraction.mapper.AttractionExtentMapper;
import com.hongpro.poortravel.service.attraction.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AttractionServiceImpl extends BaseServiceImpl<Attraction, AttractionExtentMapper> implements AttractionService<Attraction> {
    @Autowired
    AttractionExtentMapper attractionExtentMapper;
    @Override
    public List<Attraction> selectByRoute(String route) {
        Attraction attraction=new Attraction();
        attraction.setRoute(route);

        List<Attraction> attractions = attractionExtentMapper.select(attraction);
        return attractions;
    }

    @Override
    public List<Attraction> search(String key) {
        Example example=new Example(Attraction.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().orLike("title", likekey).orLike("address",likekey);
        }

        List<Attraction> attractions = attractionExtentMapper.selectByExample(example);
        return attractions;
    }

    @Override
    public List<Attraction> selectList(String id) {
        Attraction attraction = new Attraction();
        attraction.setId(Integer.valueOf(id));
        return attractionExtentMapper.select(attraction);
    }

    @Override
    public List<Attraction> selectByCondiction(Attraction attraction) {

        return attractionExtentMapper.select(attraction);
    }
}
