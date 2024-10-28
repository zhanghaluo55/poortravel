package com.hongpro.poortravel.service.posts.service.impl;

import com.hongpro.poortravel.common.domain.PostCommentReply;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.posts.mapper.PostCommentReplyExtentMapper;
import com.hongpro.poortravel.service.posts.service.PostCommentReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class PostCommentReplyServiceImpl extends BaseServiceImpl<PostCommentReply, PostCommentReplyExtentMapper> implements PostCommentReplyService<PostCommentReply> {
    @Autowired
    private PostCommentReplyExtentMapper postCommentReplyExtentMapper;

}
