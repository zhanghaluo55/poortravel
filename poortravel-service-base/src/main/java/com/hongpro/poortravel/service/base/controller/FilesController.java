package com.hongpro.poortravel.service.base.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.AttractionFiles;
import com.hongpro.poortravel.common.domain.Files;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.base.service.FilesService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/files")
public class FilesController {
    @Autowired
    private FilesService<Files> filesService;

    /**
     * 根据ID获取files
     * @param fileGuid
     * @return
     */
     @RequestMapping(value = "{fileGuid}", method = RequestMethod.GET)
     public BaseResult get(@PathVariable(value = "fileGuid") String fileGuid) {
         Files files = new Files();
         files.setId(Integer.valueOf(fileGuid));
         Files obj = filesService.selectOne(files);
         System.out.println("get:"+obj.getId());
        return BaseResult.ok(obj);
     }

     /**
     * 保存文章
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String fileJson) {
        int result = 0;

        Files files = null;
        try {
            files = MapperUtils.json2pojo(fileJson, Files.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("filejson:"+fileJson);
        if (files != null) {
            //新增文章
            result = filesService.insert(files);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 文章分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param fileJson
     * @return
     */
    @ApiOperation(value = "文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "fileJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "fileJson") String fileJson
    ) {
        Files files = null;
        try {
            files = MapperUtils.json2pojo(fileJson, Files.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(files);
        PageInfo pageInfo = filesService.page(pageNum, pageSize, files);
        List<Files> list = pageInfo.getList();
        System.out.println("list:" + list);
        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        BaseResult baseResult = BaseResult.ok(list, cursor);
        System.out.println("baseResult:" + baseResult);
        return baseResult;
    }

    /**
     * 根据id删除文章
     *
     * @param fileGuid
     * @return
     */
    @RequestMapping(value = "{fileGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "fileGuid") String fileGuid) {

        Files files = new Files();
        files.setId(Integer.valueOf(fileGuid));
        int result = 0;

        result = filesService.delete(files);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新文章
     *
     * @param fileJson
     * @return
     */
    @RequestMapping(value = "{fileGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "fileGuid") String fileGuid, @RequestParam(required = true) String fileJson) {
        int result = 0;

        Files files = null;
        try {
            files = MapperUtils.json2pojo(fileJson, Files.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(files.getFilename());
        result = filesService.update(files);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    /**
     * 批量查找根据ID获取files
     * @param
     * @return
     */
    @RequestMapping(value = "some", method = RequestMethod.GET)
    public BaseResult getSome(@RequestParam(required = true) String attrfileslist) throws Exception {
        List<AttractionFiles> objects = MapperUtils.json2list(attrfileslist, AttractionFiles.class);
        System.out.println(objects);
        List<Files> result=new ArrayList<>();
        Files temp=new Files();
        for(int i=0;i<objects.size();i++){
            temp.setId(objects.get(i).getFid());
            Files flies=filesService.selectOne(temp);
            result.add(flies);
        }

        return BaseResult.ok(result);
    }
    /**
     * 获取files
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {

        List<Files> obj = filesService.getAll();
        System.out.println("size:"+obj.size());
        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<Files> list=filesService.search(key);

        return BaseResult.ok(list);
    }
}
