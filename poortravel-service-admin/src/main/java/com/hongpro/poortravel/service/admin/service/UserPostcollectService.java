package com.hongpro.poortravel.service.admin.service;

import com.hongpro.poortravel.common.domain.UserPostcollect;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;

/**
 * Created by Rainer on 2020/1/10.
 */
public interface UserPostcollectService<T> extends BaseService<T> {
    public List<UserPostcollect> selectListUserPostcollect(String uid);
    public List<UserPostcollect> search(String key);
}
