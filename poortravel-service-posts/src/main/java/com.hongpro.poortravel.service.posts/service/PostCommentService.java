package com.hongpro.poortravel.service.posts.service;

import com.hongpro.poortravel.common.domain.PostComment;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface PostCommentService<T> extends BaseService<T> {
    public List<PostComment> selectListPostComment(String pid);
}
