package com.hongpro.poortravel.service.attraction.service;

import com.hongpro.poortravel.common.domain.Attraction;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface AttractionService<T> extends BaseService<T> {
    public List<Attraction> selectByRoute(String route);
    public List<Attraction> search(String key);
    public List<Attraction> selectList(String id);
    public List<Attraction> selectByCondiction(Attraction attraction);
}
