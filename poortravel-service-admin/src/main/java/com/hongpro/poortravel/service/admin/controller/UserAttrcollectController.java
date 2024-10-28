package com.hongpro.poortravel.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.UserAttrcollect;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.admin.service.UserAttrcollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/userattrcollects")
public class UserAttrcollectController {
    @Autowired
    private UserAttrcollectService<UserAttrcollect> userAttrcollectService;

    /**
     * 根据ID获取users
     *
     * @param
     * @return
     */
    @RequestMapping(value = "{userAttrcollectGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userAttrcollectGuid") String userAttrcollectGuid) {
        UserAttrcollect userAttrcollect = new UserAttrcollect();
        userAttrcollect.setId(Integer.valueOf(userAttrcollectGuid));
        UserAttrcollect obj = userAttrcollectService.selectOne(userAttrcollect);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String userAttrcollectJson
    ) {
        UserAttrcollect userAttrcollect = null;
        try {
            userAttrcollect = MapperUtils.json2pojo(userAttrcollectJson, UserAttrcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = userAttrcollectService.page(pageNum, pageSize, userAttrcollect);
        List<UserAttrcollect> list = pageInfo.getList();
        System.out.println("userAttrcollectlist:" + list);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }

    /**
     * 保存用户
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String userAttrcollectJson) {
        int result = 0;

        UserAttrcollect userAttrcollect = null;
        try {
            userAttrcollect = MapperUtils.json2pojo(userAttrcollectJson, UserAttrcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userAttrcollectService.insert(userAttrcollect);
        if (result > 0) {
            return BaseResult.ok("保存成功");
        }

        return BaseResult.ok("保存失败");
    }


    /**
     * 根据id删除用户
     *
     * @param userAttrcollectGuid
     * @return
     */
    @RequestMapping(value = "{userAttrcollectGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "userAttrcollectGuid") String userAttrcollectGuid) {

        UserAttrcollect userAttrcollect = new UserAttrcollect();
        userAttrcollect.setId(Integer.valueOf(userAttrcollectGuid));
        int result = 0;

        result = userAttrcollectService.delete(userAttrcollect);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新用户
     *
     * @param userAttrcollectJson
     * @return
     */
    @RequestMapping(value = "{userAttrcollectGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "userAttrcollectGuid") String userAttrcollectGuid, @RequestParam(required = true) String userAttrcollectJson) {
        int result = 0;
        System.out.println("updateJson:"+userAttrcollectJson);
        UserAttrcollect userAttrcollect = null;
        try {
            userAttrcollect = MapperUtils.json2pojo(userAttrcollectJson, UserAttrcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userAttrcollectService.update(userAttrcollect);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }

        return BaseResult.ok("更新失败");
    }

    /**
     * 获取所有userAttrcollect
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<UserAttrcollect> list = userAttrcollectService.getAll();
        System.out.println("listsize:"+list.size());
        return BaseResult.ok(list);
    }

    @RequestMapping(value = "list/{userGuid}", method = RequestMethod.GET)
    public BaseResult getUserAttrcollectList(@PathVariable(value = "userGuid") String userGuid) {
        List<UserAttrcollect> obj = userAttrcollectService.selectListUserAttrcollect(userGuid);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "delete/{uid}/{aid}", method = RequestMethod.DELETE)
    public BaseResult deleteByCondition(@PathVariable(value = "uid") String uid,@PathVariable(value = "aid") String aid) {
        UserAttrcollect userAttrcollect = new UserAttrcollect();
        int result = 0 ;
        userAttrcollect.setUid(Integer.valueOf(uid));
        userAttrcollect.setAid(Integer.valueOf(aid));
        result = userAttrcollectService.delete(userAttrcollect);
        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }
}
