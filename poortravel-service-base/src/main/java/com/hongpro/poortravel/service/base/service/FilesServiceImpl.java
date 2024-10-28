package com.hongpro.poortravel.service.base.service;

import com.hongpro.poortravel.common.domain.Files;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.base.mapper.FilesExtentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FilesServiceImpl extends BaseServiceImpl<Files,FilesExtentMapper> implements FilesService<Files> {
    @Autowired
    private FilesExtentMapper filesExtentMapper;

    @Override
    public List<Files> search(String key) {
        Example example=new Example(Files.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().orLike("filename", likekey).orLike("filetype",likekey).orLike("path",likekey);
        }

        List<Files> files = filesExtentMapper.selectByExample(example);
        return files;
    }
}
