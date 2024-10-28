package com.hongpro.poortravel.service.attraction.service.impl;

import com.hongpro.poortravel.common.domain.AttractionFiles;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.attraction.mapper.AttractionFilesExtentMapper;
import com.hongpro.poortravel.service.attraction.service.AttractionFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AttractionFilesServiceImpl extends BaseServiceImpl<AttractionFiles, AttractionFilesExtentMapper> implements AttractionFilesService<AttractionFiles> {
    @Autowired
    private AttractionFilesExtentMapper attractionFilesExtentMapper;

    public List<AttractionFiles> selectListAttrFiles(String aid){
        AttractionFiles attractionFiles=new AttractionFiles();
        attractionFiles.setAid(Integer.valueOf(aid));
        return attractionFilesExtentMapper.select(attractionFiles);
    }

    @Override
    public List<AttractionFiles> search(String key) {
        Example example=new Example(AttractionFiles.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().andLike("filetype",likekey);
        }

        List<AttractionFiles> attractionFiles = attractionFilesExtentMapper.selectByExample(example);
        return attractionFiles;
    }

}
