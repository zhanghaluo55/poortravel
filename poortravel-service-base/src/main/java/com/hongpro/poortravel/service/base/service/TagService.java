package com.hongpro.poortravel.service.base.service;

import com.hongpro.poortravel.common.domain.Tag;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface TagService<T> extends BaseService<T> {
    public List<Tag> search(String key);
}
