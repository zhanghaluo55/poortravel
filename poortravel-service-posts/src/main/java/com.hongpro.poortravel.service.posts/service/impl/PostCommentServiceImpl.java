package com.hongpro.poortravel.service.posts.service.impl;

import com.hongpro.poortravel.common.domain.PostComment;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.posts.mapper.PostCommentExtentMapper;
import com.hongpro.poortravel.service.posts.service.PostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostCommentServiceImpl extends BaseServiceImpl<PostComment, PostCommentExtentMapper> implements PostCommentService<PostComment> {
    @Autowired
    private PostCommentExtentMapper postCommentExtentMapper;

    @Override
    public List<PostComment> selectListPostComment(String pid) {
        PostComment postComment = new PostComment();
        postComment.setPid(Integer.valueOf(pid));
        return postCommentExtentMapper.select(postComment);
    }
}
