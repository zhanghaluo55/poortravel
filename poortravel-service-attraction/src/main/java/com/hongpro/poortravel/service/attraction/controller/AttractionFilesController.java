package com.hongpro.poortravel.service.attraction.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.AttractionFiles;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.attraction.service.AttractionFilesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/attractionfiles")
public class AttractionFilesController {
    @Autowired
    private AttractionFilesService<AttractionFiles> attractionFilesService;

    /**
     * 获取所有attractionFile
     *
     * @return
     */
    @RequestMapping( value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<AttractionFiles> list  = attractionFilesService.getAll();
        return BaseResult.ok(list);
    }

    /**
     * 保存攻略图片
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String attractionFilesJson) {
        int result = 0;

        AttractionFiles attractionFiles = null;
        try {
            attractionFiles = MapperUtils.json2pojo(attractionFilesJson, AttractionFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (attractionFiles != null) {

            result = attractionFilesService.insert(attractionFiles);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 根据ID获取attractionFiles
     *
     * @param attractionFilesGuid
     * @return
     */
    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid) {
        AttractionFiles attractionFiles = new AttractionFiles();
        attractionFiles.setId(Integer.valueOf(attractionFilesGuid));
        AttractionFiles obj = attractionFilesService.selectOne(attractionFiles);

        return BaseResult.ok(obj);
    }


    /**
     * 根据ID获取attractionFilesList
     *
     * @param attractionGuid
     * @return
     */
    @RequestMapping(value = "list/{attractionGuid}", method = RequestMethod.GET)
    public BaseResult getAttrFilesList(@PathVariable(value = "attractionGuid") String attractionGuid) {
        List<AttractionFiles> obj = attractionFilesService.selectListAttrFiles(attractionGuid);

        return BaseResult.ok(obj);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param attractionFilesJson
     * @return
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "attractionFilesJson", value = "对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String attractionFilesJson
    ) {
        AttractionFiles attractionFiles = null;
        try {
            attractionFiles = MapperUtils.json2pojo(attractionFilesJson, AttractionFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(attractionFiles);
        PageInfo pageInfo = attractionFilesService.page(pageNum, pageSize, attractionFiles);
        List<AttractionFiles> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除
     *
     * @param attractionFilesGuid
     * @return
     */
    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid) {

        AttractionFiles attractionFiles = new AttractionFiles();
        attractionFiles.setId(Integer.valueOf(attractionFilesGuid));
        int result = 0;

        result = attractionFilesService.delete(attractionFiles);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新
     *
     * @param attractionFilesJson
     * @return
     */
    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid, @RequestParam(required = true) String attractionFilesJson) {
        int result = 0;

        AttractionFiles attractionFiles = null;
        try {
            attractionFiles = MapperUtils.json2pojo(attractionFilesJson, AttractionFiles.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = attractionFilesService.update(attractionFiles);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<AttractionFiles> list=attractionFilesService.selectListAttrFiles(key);

        return BaseResult.ok(list);
    }
}
