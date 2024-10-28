package com.hongpro.poortravel.common.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk_mybatis.mapper.MyMapper;

import java.util.List;

@Service
@Transactional(readOnly = true)
public abstract class BaseServiceImpl<T, D extends MyMapper<T>> implements BaseService<T> {

    @Autowired
    private D dao;

    @Override
    @Transactional(readOnly = false)
    public int insert(T t) {
        return dao.insert(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(T t) {
        return dao.delete(t);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(T t) {
        return dao.updateByPrimaryKeySelective(t);
    }

    @Override
    public T selectOne(T t) {
        return dao.selectOne(t);
    }

    @Override
    public int count(T t) {
        return dao.selectCount(t);
    }

    @Override
    public PageInfo<T> page(int pageNum, int pageSize, T t) {
        PageHelper pageHelper = new PageHelper();
        pageHelper.startPage(pageNum, pageSize);

        PageInfo<T> pageInfo = new PageInfo<>(dao.select(t));
        return pageInfo;

    }

    @Override
    public List<T> getAll() {
        return dao.selectAll();
    }
}
