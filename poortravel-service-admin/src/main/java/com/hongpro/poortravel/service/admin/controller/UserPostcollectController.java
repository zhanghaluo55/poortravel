package com.hongpro.poortravel.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.UserPostcollect;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.admin.service.UserPostcollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/userpostcollects")
public class UserPostcollectController {
    @Autowired
    private UserPostcollectService<UserPostcollect> userPostcollectService;

    /**
     * 根据ID获取userPostcollect
     *
     * @param userPostcollectGuid
     * @return
     */
    @RequestMapping(value = "{userPostcollectGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userPostcollectGuid") String userPostcollectGuid) {
        UserPostcollect userPostcollect = new UserPostcollect();
        userPostcollect.setId(Integer.valueOf(userPostcollectGuid));
        UserPostcollect obj = userPostcollectService.selectOne(userPostcollect);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String userPostcollectJson
    ) {
        UserPostcollect userPostcollect = null;
        try {
            userPostcollect = MapperUtils.json2pojo(userPostcollectJson, UserPostcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = userPostcollectService.page(pageNum, pageSize, userPostcollect);
        List<UserPostcollect> list = pageInfo.getList();
        System.out.println("userPostcollectlist:" + list);
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
    public BaseResult save(@RequestParam(required = true) String userPostcollectJson) {
        int result = 0;

        UserPostcollect userPostcollect = null;
        try {
            userPostcollect = MapperUtils.json2pojo(userPostcollectJson, UserPostcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userPostcollectService.insert(userPostcollect);
        if (result > 0) {
            return BaseResult.ok("保存成功");
        }

        return BaseResult.ok("保存失败");
    }


    /**
     * 根据id删除用户
     *
     * @param userPostcollectGuid
     * @return
     */
    @RequestMapping(value = "{userPostcollectGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "userPostcollectGuid") String userPostcollectGuid) {

        UserPostcollect userPostcollect = new UserPostcollect();
        userPostcollect.setId(Integer.valueOf(userPostcollectGuid));
        int result = 0;

        result = userPostcollectService.delete(userPostcollect);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新用户
     *
     * @param userPostcollectJson
     * @return
     */
    @RequestMapping(value = "{userPostcollectGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "userPostcollectGuid") String userPostcollectGuid, @RequestParam(required = true) String userPostcollectJson) {
        int result = 0;
        System.out.println("updateJson:"+userPostcollectJson);
        UserPostcollect userPostcollect = null;
        try {
            userPostcollect = MapperUtils.json2pojo(userPostcollectJson, UserPostcollect.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userPostcollectService.update(userPostcollect);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }

        return BaseResult.ok("更新失败");
    }

    /**
     * 获取所有userPostcollect
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<UserPostcollect> list = userPostcollectService.getAll();
        System.out.println("listsize:"+list.size());
        return BaseResult.ok(list);
    }

    @RequestMapping(value = "list/{userGuid}", method = RequestMethod.GET)
    public BaseResult getUserPostcollectList(@PathVariable(value = "userGuid") String userGuid) {
        List<UserPostcollect> obj = userPostcollectService.selectListUserPostcollect(userGuid);
        System.out.println("size:"+obj.size());
        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "delete/{uid}/{pid}", method = RequestMethod.DELETE)
    public BaseResult deleteByCondition(@PathVariable(value = "uid")String uid,@PathVariable(value = "pid")String pid ) {
        UserPostcollect userPostcollect = new UserPostcollect();
        userPostcollect.setUid(Integer.valueOf(uid));
        userPostcollect.setPid(Integer.valueOf(pid));
        int result = 0 ;
//
//        try {
//            userPostcollect = MapperUtils.json2pojo(userPostcollectJson, UserPostcollect.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        System.out.println("pid:"+userPostcollect.getPid());
        System.out.println("uid:"+userPostcollect.getUid());
        System.out.println("id:"+userPostcollect.getId());
        result = userPostcollectService.delete(userPostcollect);
        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }
}
