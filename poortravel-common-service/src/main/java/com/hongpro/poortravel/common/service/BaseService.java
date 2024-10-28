package com.hongpro.poortravel.common.service;

import com.github.pagehelper.PageInfo;

import java.util.List;

public interface BaseService<T> {
    int insert(T t);

    int delete(T t);

    int update(T t);

    T selectOne(T t);

    int count(T t);

    PageInfo<T> page(int pageNum, int pageSize, T t);

    List<T> getAll();
}
