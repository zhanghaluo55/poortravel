package com.hongpro.poortravel.service.admin.test.service;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.service.admin.ServiceAdminApplication;
import com.hongpro.poortravel.service.admin.service.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceAdminApplication.class)
@ActiveProfiles(value = "prod")
public class AdminServiceTest {
    @Autowired
    private AdminService adminService;

    /**
     * 注册
     */
    @Test
    public void register() {
        User user = new User();
        user.setId(1);
        user.setUsername("zhanghaluo55");
        user.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        user.setAirphone("13565142481");
        user.setEmail("135651421@qq.com");
        user.setGender(1);
        user.setNickname("Rainer");
        user.setRegDate(new Date());
        user.setStatus(1);
        user.setUsertype(1);
        adminService.register(user);

    }

    /**
     * 登录
     */
    @Test
    public void login() {
        User user = adminService.login("zhanghaluo55", "123456");
        Assert.assertNotNull(user);
    }
}
