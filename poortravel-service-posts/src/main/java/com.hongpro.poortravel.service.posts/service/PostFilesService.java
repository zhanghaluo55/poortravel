package com.hongpro.poortravel.service.posts.service;

import com.hongpro.poortravel.common.domain.PostFiles;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface PostFilesService<T> extends BaseService<T> {
    public List<PostFiles> selectListPostFiles(String pid);
}
