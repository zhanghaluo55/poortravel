package com.hongpro.poortravel.service.posts.service.impl;

import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.posts.mapper.PostsExtentMapper;
import com.hongpro.poortravel.service.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PostsServiceImpl extends BaseServiceImpl<Post, PostsExtentMapper> implements PostsService<Post> {
    @Autowired
    private PostsExtentMapper postsExtentMapper;

    @Override
    public List<Post> search(String key) {
        Example example=new Example(Post.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().orLike("title", likekey).orLike("province",likekey);
        }

        List<Post> posts = postsExtentMapper.selectByExample(example);
        return posts;
    }

    @Override
    public List<Post> selectByProvince(String province) {
        Post post=new Post();
        post.setProvince(province);

        List<Post> posts = postsExtentMapper.select(post);
        return posts;
    }

    @Override
    public List<Post> selectByUid(String uid) {
        Post post=new Post();
        post.setUid(Integer.valueOf(uid));
        return postsExtentMapper.select(post);
    }
}
