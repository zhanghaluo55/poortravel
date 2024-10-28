package com.hongpro.poortravel.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.CookieUtils;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "v1/admins")
public class AdminController {
    @Autowired
    private AdminService<User> adminService;

    /**
     * 根据ID获取users
     *
     * @param userGuid
     * @return
     */
    @RequestMapping(value = "{userGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userGuid") String userGuid) {
        User user = new User();
        user.setId(Integer.valueOf(userGuid));
        User obj = adminService.selectOne(user);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String userJson
    ) {
        User user = null;
        try {
            user = MapperUtils.json2pojo(userJson, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = adminService.page(pageNum, pageSize, user);
        List<User> list = pageInfo.getList();
        System.out.println("userlist:" + list);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }

    /**
     * @author: Rainer
     * @description: TODO
     * @date: 2020/1/22 23:28
     * @param username
     * @param password
     * @return com.hongpro.poortravel.common.dto.BasueResult
     **/
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public BaseResult login(String username, String password, HttpServletRequest request){
        BaseResult baseResult=checkLogin(username,password);
        if(baseResult!=null){
            return baseResult;
        }

        User user=adminService.login(username,password);

        //登录成功
        if(user!=null){
            System.out.println("登录成功");
            request.getSession().setAttribute("user",user);
            return BaseResult.ok(user);
        }
        //登录失败
        else {
            System.out.println("登录失败");
            return BaseResult.notOk(Lists.newArrayList(new BaseResult.Error("","登录失败")));
        }
    }
    /**
     * 检查登录
     * @param username
     * @param password
     * @return
     */
    private BaseResult checkLogin(String username,String password){
        BaseResult baseResult=null;

        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            baseResult=BaseResult.notOk(Lists.newArrayList(
                    new BaseResult.Error("username","账号不能为空"),
                    new BaseResult.Error("password","密码不能为空")
            ));

        }
        return baseResult;
    }

    /**
     * 保存用户
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String userJson) {
        int result = 0;

        User user = null;
        try {
            user = MapperUtils.json2pojo(userJson, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setUsertype(2);
        user.setStatus(1);
        user.setRegDate(new Date());
        user.setGender(1);
        user.setHeadImage("http://111.230.171.37:8888/group1/M00/00/00/rBAADl5439iACufbAAB_5ku9yEU708.jpg");
        user.setAddress("保密");
        if (user != null) {
            //密码加密
            String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
            user.setPassword(password);
            //新增用户
            result = adminService.insert(user);
            /*
            if(StringUtils.isBlank(String.valueOf(user.getId()))){
                user.setId(UUID.randomUUID());
            }
            */
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }


    /**
     * 根据id删除用户
     *
     * @param userGuid
     * @return
     */
    @RequestMapping(value = "{userGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "userGuid") String userGuid) {

        User user = new User();
        user.setId(Integer.valueOf(userGuid));
        int result = 0;

        result = adminService.delete(user);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新用户
     *
     * @param userJson
     * @return
     */
    @RequestMapping(value = "{userGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "userGuid") String userGuid, @RequestParam(required = true) String userJson) {
        int result = 0;
        System.out.println("updateJson:"+userJson);
        User user = null;
        try {
            user = MapperUtils.json2pojo(userJson, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user != null) {
            //密码加密
            if(user.getPassword()!=null){
                String password = DigestUtils.md5DigestAsHex(user.getPassword().getBytes());
                user.setPassword(password);
            }
            result = adminService.update(user);
            if (result > 0) {
                return BaseResult.ok("更新成功");
            }
        }
        return BaseResult.ok("更新失败");
    }

    /**
     * 注销
     *
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value = "loginout", method = RequestMethod.GET)
    public String loginout(HttpServletRequest request, HttpServletResponse response) {
        try {
            CookieUtils.deleteCookie(request, response, "token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.getSession().removeAttribute("user");
        return "ok";
    }

    /**
     * 获取所有users
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<User> list = adminService.getAll();
        System.out.println("listsize:"+list.size());
        return BaseResult.ok(list);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<User> list=adminService.search(key);

        return BaseResult.ok(list);
    }
}
