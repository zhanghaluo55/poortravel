package com.hongpro.poortravel.service.base.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.Tag;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.base.service.TagService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/tags")
public class TagController {
    @Autowired
    private TagService<Tag> tagService;

    /**
     * 根据ID获取tag
     * @param tagGuid
     * @return
     */
    @RequestMapping(value = "{tagGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "tagGuid") String tagGuid) {
        Tag tag = new Tag();
        tag.setId(Integer.valueOf(tagGuid));
        Tag obj = tagService.selectOne(tag);

        return BaseResult.ok(obj);
    }

    /**
     * 保存文章
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String tagJson) {
        int result = 0;

        Tag tag = null;
        try {
            tag = MapperUtils.json2pojo(tagJson, Tag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("tagjson:"+tagJson);
        if (tag != null) {
            //新增文章
            result = tagService.insert(tag);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 标签分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param tagJson
     * @return
     */
    @ApiOperation(value = "标签分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "tagJson", value = "标签对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "tagJson") String tagJson
    ) {
        System.out.println("flag");
        System.out.println("pageNum:" + pageNum);
        System.out.println("pageSize:" + pageSize);
        Tag tag = null;
        try {
            tag = MapperUtils.json2pojo(tagJson, Tag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(tag);
        PageInfo pageInfo = tagService.page(pageNum, pageSize, tag);
        List<Tag> list = pageInfo.getList();
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
     * @param tagGuid
     * @return
     */
    @RequestMapping(value = "{tagGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "tagGuid") String tagGuid) {

        Tag tag = new Tag();
        tag.setId(Integer.valueOf(tagGuid));
        int result = 0;

        result = tagService.delete(tag);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新文章
     *
     * @param tagJson
     * @return
     */
    @RequestMapping(value = "{tagGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "tagGuid") String tagGuid, @RequestParam(required = true) String tagJson) {
        int result = 0;

        Tag tag = null;
        try {
            tag = MapperUtils.json2pojo(tagJson, Tag.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = tagService.update(tag);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    /**
     * 批量查找根据ID获取tag
     * @param
     * @return
     */
//    @RequestMapping(value = "some", method = RequestMethod.GET)
//    public BaseResult getSome(@RequestParam(required = true) String attrtaglist) throws Exception {
//        List<AttractionTag> objects = MapperUtils.json2list(attrtaglist, AttractionTag.class);
//        System.out.println(objects);
//        List<Tag> result=new ArrayList<>();
//        Tag temp=new Tag();
//        for(int i=0;i<objects.size();i++){
//            temp.setId(objects.get(i).getTid());
//            Tag tag=tagService.selectOne(temp);
//            result.add(tag);
//        }
//
//        return BaseResult.ok(result);
//    }

    /**
     * 获取tags
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {

        List<Tag> obj = tagService.getAll();
        return BaseResult.ok(obj);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<Tag> list=tagService.search(key);

        return BaseResult.ok(list);
    }
}
