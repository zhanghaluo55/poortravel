package com.hongpro.poortravel.service.posts.service.impl;

import com.hongpro.poortravel.common.domain.PostTag;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.posts.mapper.PostTagExtentMapper;
import com.hongpro.poortravel.service.posts.service.PostTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostTagServiceImpl extends BaseServiceImpl<PostTag, PostTagExtentMapper> implements PostTagService<PostTag> {
    @Autowired
    private PostTagExtentMapper postTagExtentMapper;

    @Override
    public List<PostTag> selectListPostTags(String id) {
        PostTag postTag=new PostTag();
        postTag.setPid(Integer.valueOf(id));
        return postTagExtentMapper.select(postTag);
    }
}
