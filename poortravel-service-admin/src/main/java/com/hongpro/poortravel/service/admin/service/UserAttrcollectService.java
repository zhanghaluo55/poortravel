package com.hongpro.poortravel.service.admin.service;

import com.hongpro.poortravel.common.domain.UserAttrcollect;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;

/**
 * Created by Rainer on 2020/1/10.
 */
public interface UserAttrcollectService<T> extends BaseService<T> {
    public List<UserAttrcollect> selectListUserAttrcollect(String uid);
    public List<UserAttrcollect> search(String key);
}
