package com.hongpro.poortravel.service.sso.mapper;


import com.hongpro.poortravel.common.domain.User;
import org.springframework.stereotype.Repository;
import tk_mybatis.mapper.MyMapper;
@Repository
public interface UserExtentMapper extends MyMapper<User> {
}