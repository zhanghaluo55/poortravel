package com.hongpro.poortravel.service.posts.service;

import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface PostsService<T> extends BaseService<T> {
    public List<Post> search(String key);
    public List<Post> selectByProvince(String province);
    public List<Post> selectByUid(String uid);
}
