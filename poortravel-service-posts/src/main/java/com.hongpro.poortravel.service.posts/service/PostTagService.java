package com.hongpro.poortravel.service.posts.service;

import com.hongpro.poortravel.common.domain.PostTag;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface PostTagService<T> extends BaseService<T> {
    public List<PostTag> selectListPostTags(String id);
}
