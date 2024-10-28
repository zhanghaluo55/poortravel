package com.hongpro.poortravel.service.attraction.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.*;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.attraction.service.AttractionFilesService;
import com.hongpro.poortravel.service.attraction.service.AttractionService;
import com.hongpro.poortravel.service.attraction.service.AttractionTagService;
import com.hongpro.poortravel.service.attraction.service.consumer.FilesService;
import com.hongpro.poortravel.service.attraction.service.consumer.TagService;
import com.hongpro.poortravel.service.attraction.service.consumer.UserAttrcollectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/attractions")
public class AttractionController {
    @Autowired
    private AttractionService<Attraction> attractionService;
    @Autowired
    private AttractionFilesService<AttractionFiles> attractionFilesService;
    @Autowired
    private AttractionTagService<AttractionTag> attractionTagService;
    @Autowired
    private FilesService filesService;
    @Autowired
    private TagService tagService;
    @Autowired
    private UserAttrcollectService userAttrcollectService;

    /**
     * 根据ID获取attraction
     *
     * @param attractionGuid
     * @return
     */
    @RequestMapping(value = "{attractionGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "attractionGuid") String attractionGuid) {
        Attraction obj = new Attraction();
        obj.setId(Integer.valueOf(attractionGuid));
        Attraction attraction = attractionService.selectOne(obj);

        List<AttractionFiles> attractionFiles = attractionFilesService.selectListAttrFiles(attractionGuid);
        String filesJson = filesService.getSome(JSON.toJSONString(attractionFiles));
        System.out.println("filesJson"+filesJson);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(filesJson, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("baseResult"+baseResult);
        attraction.setFiles((List<Files>) baseResult.getData());

        return BaseResult.ok(attraction);
    }

    /**
     * 根据ID获取attraction
     *
     * @param attractionGuid
     * @return
     */
    @RequestMapping(value = "/get/{attractionGuid}", method = RequestMethod.GET)
    public BaseResult getOne(@PathVariable(value = "attractionGuid") String attractionGuid){
        Attraction obj = new Attraction();
        obj.setId(Integer.valueOf(attractionGuid));
        Attraction attraction = attractionService.selectOne(obj);
        return BaseResult.ok(attraction);
    }

    /**
     * 保存攻略
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String attractionJson) {
        int result = 0;

        Attraction attraction = null;
        try {
            attraction = MapperUtils.json2pojo(attractionJson, Attraction.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (attraction != null) {
            //新增攻略
            result = attractionService.insert(attraction);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 攻略分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param attractionJson
     * @return
     */
    @ApiOperation(value = "攻略分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "attractionJson", value = "攻略对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String attractionJson
    ) {
        Attraction attraction = null;
        try {
            attraction = MapperUtils.json2pojo(attractionJson, Attraction.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(attraction);
        PageInfo pageInfo = attractionService.page(pageNum, pageSize, attraction);
        List<Attraction> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除攻略
     *
     * @param attractionGuid
     * @return
     */
    @RequestMapping(value = "{attractionGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "attractionGuid") String attractionGuid) {

        Attraction attraction = new Attraction();
        attraction.setId(Integer.valueOf(attractionGuid));
        int result = 0;

        result = attractionService.delete(attraction);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新攻略
     *
     * @param attractionJson
     * @return
     */
    @RequestMapping(value = "{attractionGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "attractionGuid") String attractionGuid, @RequestParam(required = true) String attractionJson) {
        int result = 0;

        Attraction attraction = null;
        try {
            attraction = MapperUtils.json2pojo(attractionJson, Attraction.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = attractionService.update(attraction);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    /**
     * 获取所有attraction
     *
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll(){
        List<Attraction> attractions = attractionService.getAll();

        return BaseResult.ok(attractions);
    }

    /**
     * 获取所有attraction
     *
     * @param
     * @return
     */
    @RequestMapping(value = "searchroute", method = RequestMethod.GET)
    public BaseResult searchByRoute(@RequestParam(required = false) String route ){
        System.out.println(route);
        List<Attraction> attractions = attractionService.selectByRoute(route);

        List<Attraction> result=new ArrayList<>();

        for(Attraction attraction1:attractions){
            List<AttractionTag> attractionTag = attractionTagService.selectListAttrTags(attraction1.getId().toString());
            attraction1.setAttractionTags(attractionTag);

            result.add(attraction1);
        }

        return BaseResult.ok(result);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<Attraction> list=attractionService.search(key);

        return BaseResult.ok(list);
    }

    @RequestMapping(value = "collect/{userGuid}", method = RequestMethod.GET)
    public BaseResult selectCollect(@PathVariable(value = "userGuid") String userGuid) throws Exception {
        String json=userAttrcollectService.getUserAttrcollectList(userGuid);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        String json2=MapperUtils.obj2json(baseResult.getData());
        List<UserAttrcollect> userAttrcollects = MapperUtils.json2list(json2, UserAttrcollect.class);
        List<Attraction> result = new ArrayList<>();
        Attraction temp = new Attraction();
        for (UserAttrcollect userAttrcollect: userAttrcollects){
            temp.setId(userAttrcollect.getAid());
            result.add(attractionService.selectOne(temp));
        }

        return BaseResult.ok(result);
    }


    @RequestMapping(value = "searchpsb", method = RequestMethod.GET)
    public BaseResult searchBySomething(@RequestParam(required = false) Attraction attraction){

        List<Attraction> attractions = attractionService.selectByCondiction(attraction);

        return BaseResult.ok(attractions);
    }

}
