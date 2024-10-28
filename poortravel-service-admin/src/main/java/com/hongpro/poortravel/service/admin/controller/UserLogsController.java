package com.hongpro.poortravel.service.admin.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.UserLogs;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.admin.service.UserLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/userlogs")
public class UserLogsController {
    @Autowired
    private UserLogsService<UserLogs> userLogsService;

    /**
     * 根据ID获取userLogss
     *
     * @param userLogsGuid
     * @return
     */
    @RequestMapping(value = "{userLogsGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "userLogsGuid") String userLogsGuid) {
        UserLogs userLogs = new UserLogs();
        userLogs.setId(Integer.valueOf(userLogsGuid));
        UserLogs obj = userLogsService.selectOne(userLogs);

        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String userLogsJson
    ) {
        UserLogs userLogs = null;
        try {
            userLogs = MapperUtils.json2pojo(userLogsJson, UserLogs.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo = userLogsService.page(pageNum, pageSize, userLogs);
        List<UserLogs> list = pageInfo.getList();
        System.out.println("userLogslist:" + list);
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
    public BaseResult save(@RequestParam(required = true) String userLogsJson) {
        int result = 0;

        UserLogs userLogs = null;
        try {
            userLogs = MapperUtils.json2pojo(userLogsJson, UserLogs.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userLogsService.insert(userLogs);
        if (result > 0) {
            return BaseResult.ok("保存成功");
        }

        return BaseResult.ok("保存失败");
    }


    /**
     * 根据id删除用户
     *
     * @param userLogsGuid
     * @return
     */
    @RequestMapping(value = "{userLogsGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "userLogsGuid") String userLogsGuid) {

        UserLogs userLogs = new UserLogs();
        userLogs.setId(Integer.valueOf(userLogsGuid));
        int result = 0;

        result = userLogsService.delete(userLogs);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新用户
     *
     * @param userLogsJson
     * @return
     */
    @RequestMapping(value = "{userLogsGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "userLogsGuid") String userLogsGuid, @RequestParam(required = true) String userLogsJson) {
        int result = 0;
        System.out.println("updateJson:"+userLogsJson);
        UserLogs userLogs = null;
        try {
            userLogs = MapperUtils.json2pojo(userLogsJson, UserLogs.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = userLogsService.update(userLogs);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }

        return BaseResult.ok("更新失败");
    }

    /**
     * 获取所有userLogs
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<UserLogs> list = userLogsService.getAll();
        System.out.println("listsize:"+list.size());
        return BaseResult.ok(list);
    }

}
