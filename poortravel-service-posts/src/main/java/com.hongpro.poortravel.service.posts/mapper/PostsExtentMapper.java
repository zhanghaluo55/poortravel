package com.hongpro.poortravel.service.posts.mapper;

import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import org.springframework.stereotype.Repository;
import tk_mybatis.mapper.MyMapper;
@Repository
@CacheNamespace(implementation = RedisCache.class)
public interface PostsExtentMapper extends MyMapper<Post> {
}
