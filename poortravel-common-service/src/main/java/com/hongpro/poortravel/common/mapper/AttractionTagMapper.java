package com.hongpro.poortravel.common.mapper;

import com.hongpro.poortravel.common.domain.AttractionTag;
import com.hongpro.poortravel.common.utils.RedisCache;
import org.apache.ibatis.annotations.CacheNamespace;
import tk_mybatis.mapper.MyMapper;

@CacheNamespace(implementation = RedisCache.class)
public interface AttractionTagMapper extends MyMapper<AttractionTag> {
}