package com.hongpro.poortravel.service.posts.service.impl;

import com.hongpro.poortravel.common.domain.PostFiles;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.posts.mapper.PostFilesExtentMapper;
import com.hongpro.poortravel.service.posts.service.PostFilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostFilesServiceImpl extends BaseServiceImpl<PostFiles, PostFilesExtentMapper> implements PostFilesService<PostFiles> {
    @Autowired
    private PostFilesExtentMapper postFilesExtentMapper;

    @Override
    public List<PostFiles> selectListPostFiles(String pid) {
        PostFiles postFiles=new PostFiles();
        postFiles.setPid(Integer.valueOf(pid));
        return postFilesExtentMapper.select(postFiles);
    }
}
