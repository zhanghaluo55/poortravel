package com.hongpro.poortravel.service.sso.service.impl;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.sso.mapper.UserExtentMapper;
import com.hongpro.poortravel.service.sso.service.LoginService;
import com.hongpro.poortravel.service.sso.service.consumer.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

@Service
public class LoginServiceImpl implements LoginService {
    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private UserExtentMapper userExtentMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public User login(String userCode, String plantPassword) {
        User user = null;

        String json = redisService.get(userCode);
        //缓存没有数据
        if (json == null) {
            Example example = new Example(User.class);
            example.createCriteria().andEqualTo("username", userCode);

            user = userExtentMapper.selectOneByExample(example);
            if (user != null) {
                String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
                if (password.equals(user.getPassword())) {
                    try {
                        redisService.put(userCode, MapperUtils.obj2json(user), 60 * 60 * 24);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return user;
                } else {
                    return null;
                }
            }
        }
        //缓存有数据
        else {
            try {
                user = MapperUtils.json2pojo(json, User.class);
            } catch (Exception e) {
                logger.warn("触发熔断:{}", e.getMessage());
            }
        }

        return user;
    }
}
