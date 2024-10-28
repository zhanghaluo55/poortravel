package com.hongpro.poortravel.service.attraction.service;

import com.hongpro.poortravel.common.domain.AttractionFiles;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface AttractionFilesService<T> extends BaseService<T> {
    public List<AttractionFiles> selectListAttrFiles(String aid);
    public List<AttractionFiles> search(String key);
}
