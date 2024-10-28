package com.hongpro.poortravel.service.attraction.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.AttractionTag;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.attraction.service.AttractionTagService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/attractiontags")
public class AttractionTagController {
    @Autowired
    private AttractionTagService<AttractionTag> attractionTagService;

    /**
     * 获取所有attractionTag
     *
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<AttractionTag> list  = attractionTagService.getAll();
        return BaseResult.ok(list);
    }

    /**
     * 保存攻略标签
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String attractionTagJson) {
        int result = 0;

        AttractionTag attractionTag = null;
        try {
            attractionTag = MapperUtils.json2pojo(attractionTagJson, AttractionTag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (attractionTag != null) {

            result = attractionTagService.insert(attractionTag);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 根据ID获取attractionTag
     *
     * @param attractionTagGuid
     * @return
     */
    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "attractionTagGuid") String attractionTagGuid) {
        AttractionTag attractionTag = new AttractionTag();
        attractionTag.setId(Integer.valueOf(attractionTagGuid));
        AttractionTag obj = attractionTagService.selectOne(attractionTag);

        return BaseResult.ok(obj);
    }


    /**
     * 根据ID获取attractionTagList
     *
     * @param attractionGuid
     * @return
     */
    @RequestMapping(value = "list/{attractionGuid}", method = RequestMethod.GET)
    public BaseResult getAttrTagsList(@PathVariable(value = "attractionGuid") String attractionGuid) {
        List<AttractionTag> obj = attractionTagService.selectListAttrTags(attractionGuid);

        return BaseResult.ok(obj);
    }

    /**
     * 攻略分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param attractionTagJson
     * @return
     */
    @ApiOperation(value = "攻略分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "attractionTagJson", value = "攻略对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String attractionTagJson
    ) {
        AttractionTag attractionTag = null;
        try {
            attractionTag = MapperUtils.json2pojo(attractionTagJson, AttractionTag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(attractionTag);
        PageInfo pageInfo = attractionTagService.page(pageNum, pageSize, attractionTag);
        List<AttractionTag> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除攻略
     *
     * @param attractionTagGuid
     * @return
     */
    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "attractionTagGuid") String attractionTagGuid) {

        AttractionTag attractionTag = new AttractionTag();
        attractionTag.setId(Integer.valueOf(attractionTagGuid));
        int result = 0;

        result = attractionTagService.delete(attractionTag);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新攻略
     *
     * @param attractionTagJson
     * @return
     */
    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "attractionTagGuid") String attractionTagGuid, @RequestParam(required = true) String attractionTagJson) {
        int result = 0;

        AttractionTag attractionTag = null;
        try {
            attractionTag = MapperUtils.json2pojo(attractionTagJson, AttractionTag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = attractionTagService.update(attractionTag);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<AttractionTag> list=attractionTagService.selectListAttrTags(key);

        return BaseResult.ok(list);
    }
}
