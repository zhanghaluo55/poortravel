package com.hongpro.poortravel.service.sso.service;

import com.hongpro.poortravel.common.domain.User;

/**
 * 单点登录业务
 *
 * @author: Rainer
 * @description: TODO
 * @date: 2020/1/27 18:22
 */
public interface LoginService {
    /**
     * 单点登录
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password);
}
