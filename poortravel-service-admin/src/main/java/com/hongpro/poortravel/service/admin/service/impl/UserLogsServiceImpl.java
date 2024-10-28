package com.hongpro.poortravel.service.admin.service.impl;

import com.hongpro.poortravel.common.domain.UserLogs;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.admin.mapper.UserLogsExtentMapper;
import com.hongpro.poortravel.service.admin.service.UserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserLogsServiceImpl extends BaseServiceImpl<UserLogs, UserLogsExtentMapper> implements UserLogsService<UserLogs> {
    @Autowired
    private UserLogsExtentMapper userLogsExtentMapper;


    @Override
    public List<UserLogs> selectListUserLogs(String uid) {
        UserLogs userLogs=new UserLogs();
        userLogs.setUserId(Integer.valueOf(uid));
        return userLogsExtentMapper.select(userLogs);
    }

    @Override
    public List<UserLogs> search(String key) {
        return null;
    }
}
