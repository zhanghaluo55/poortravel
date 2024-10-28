package com.hongpro.poortravel.service.admin.service.impl;

import com.hongpro.poortravel.common.domain.UserAttrcollect;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.admin.mapper.UserAttrcollectExtentMapper;
import com.hongpro.poortravel.service.admin.service.UserAttrcollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class
UserAttrcollectServiceImpl extends BaseServiceImpl<UserAttrcollect,UserAttrcollectExtentMapper> implements UserAttrcollectService<UserAttrcollect> {
    @Autowired
    private UserAttrcollectExtentMapper userAttrcollectExtentMapper;


    @Override
    public List<UserAttrcollect> selectListUserAttrcollect(String uid) {
        UserAttrcollect userAttrcollect=new UserAttrcollect();
        userAttrcollect.setUid(Integer.valueOf(uid));
        return userAttrcollectExtentMapper.select(userAttrcollect);
    }

    @Override
    public List<UserAttrcollect> search(String key) {
        return null;
    }
}
