package com.hongpro.poortravel.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.PostFiles;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.posts.service.PostFilesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/postfiles")
public class PostFilesController {
    @Autowired
    private PostFilesService<PostFiles> postFilesService;

    /**
     * 获取所有postFile
     *
     * @return
     */
    @RequestMapping( value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<PostFiles> list  = postFilesService.getAll();
        return BaseResult.ok(list);
    }

    /**
     * 保存攻略图片
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String postFilesJson) {
        int result = 0;

        PostFiles postFiles = null;
        try {
            postFiles = MapperUtils.json2pojo(postFilesJson, PostFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (postFiles != null) {

            result = postFilesService.insert(postFiles);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 根据ID获取postFiles
     *
     * @param postFilesGuid
     * @return
     */
    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postFilesGuid") String postFilesGuid) {
        PostFiles postFiles = new PostFiles();
        postFiles.setId(Integer.valueOf(postFilesGuid));
        PostFiles obj = postFilesService.selectOne(postFiles);

        return BaseResult.ok(obj);
    }


    /**
     * 根据ID获取postFilesList
     *
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "list/{postGuid}", method = RequestMethod.GET)
    public BaseResult getAttrFilesList(@PathVariable(value = "postGuid") String postGuid) {
        List<PostFiles> obj = postFilesService.selectListPostFiles(postGuid);

        return BaseResult.ok(obj);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param postFilesJson
     * @return
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "postFilesJson", value = "对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String postFilesJson
    ) {
        PostFiles postFiles = null;
        try {
            postFiles = MapperUtils.json2pojo(postFilesJson, PostFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(postFiles);
        PageInfo pageInfo = postFilesService.page(pageNum, pageSize, postFiles);
        List<PostFiles> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除
     *
     * @param postFilesGuid
     * @return
     */
    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "postFilesGuid") String postFilesGuid) {

        PostFiles postFiles = new PostFiles();
        postFiles.setId(Integer.valueOf(postFilesGuid));
        int result = 0;

        result = postFilesService.delete(postFiles);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新
     *
     * @param postFilesJson
     * @return
     */
    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "postFilesGuid") String postFilesGuid, @RequestParam(required = true) String postFilesJson) {
        int result = 0;

        PostFiles postFiles = null;
        try {
            postFiles = MapperUtils.json2pojo(postFilesJson, PostFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = postFilesService.update(postFiles);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<PostFiles> list=postFilesService.selectListPostFiles(key);

        return BaseResult.ok(list);
    }
}
