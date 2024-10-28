package com.hongpro.poortravel.service.admin.service.impl;

import com.hongpro.poortravel.common.domain.UserPostcollect;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.admin.mapper.UserPostcollectExtentMapper;
import com.hongpro.poortravel.service.admin.service.UserPostcollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserPostcollectServiceImpl extends BaseServiceImpl<UserPostcollect,UserPostcollectExtentMapper> implements UserPostcollectService<UserPostcollect> {
    @Autowired
    private UserPostcollectExtentMapper userPostcollectExtentMapper;


    @Override
    public List<UserPostcollect> selectListUserPostcollect(String uid) {
        UserPostcollect userPostcollect=new UserPostcollect();
        userPostcollect.setUid(Integer.valueOf(uid));
        return userPostcollectExtentMapper.select(userPostcollect);
    }

    @Override
    public List<UserPostcollect> search(String key) {
        return null;
    }
}
