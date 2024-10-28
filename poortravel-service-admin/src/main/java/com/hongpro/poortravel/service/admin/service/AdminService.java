package com.hongpro.poortravel.service.admin.service;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;

/**
 * Created by Rainer on 2020/1/10.
 */
public interface AdminService<T> extends BaseService<T> {

    /**
     * 注册
     *
     * @param user
     * @return
     * @author Rainer
     */
    void register(User user);

    /**
     * 登录
     *
     * @param loginCode     登录账号
     * @param plantPassword 明文登录密码
     * @return
     * @author Rainer
     * @date
     */
    User login(String loginCode, String plantPassword);

    public List<User> search(String key);
}
