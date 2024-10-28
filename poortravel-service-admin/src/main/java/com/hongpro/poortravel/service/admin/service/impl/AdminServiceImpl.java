package com.hongpro.poortravel.service.admin.service.impl;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.admin.mapper.UserExtentMapper;
import com.hongpro.poortravel.service.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class AdminServiceImpl extends BaseServiceImpl<User, UserExtentMapper> implements AdminService<User> {
    @Autowired
    private UserExtentMapper userMapper;

    @Transactional(readOnly = false)
    @Override
    public void register(User user) {
        userMapper.insert(user);
    }


    @Override
    public User login(String loginCode, String plantPassword) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("username", loginCode);

        User user = userMapper.selectOneByExample(example);
        if (user != null) {
            String password = DigestUtils.md5DigestAsHex(plantPassword.getBytes());
            if (password.equals(user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> search(String key) {
        Example example=new Example(User.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().orLike("username", likekey).orLike("nickname",likekey);
        }
        List<User> users = userMapper.selectByExample(example);
        return users;
    }


}
