package com.hongpro.poortravel.service.admin.service;

import com.hongpro.poortravel.common.domain.UserLogs;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;

/**
 * Created by Rainer on 2020/1/10.
 */
public interface UserLogsService<T> extends BaseService<T> {
    public List<UserLogs> selectListUserLogs(String uid);
    public List<UserLogs> search(String key);

}
