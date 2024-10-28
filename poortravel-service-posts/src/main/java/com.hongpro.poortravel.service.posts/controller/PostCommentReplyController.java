package com.hongpro.poortravel.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.PostCommentReply;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.posts.service.PostCommentReplyService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/v1/postcommentreplys")
public class PostCommentReplyController {
    @Autowired
    private PostCommentReplyService<PostCommentReply> postCommentReplyService;

    /**
     * 获取所有postCommentReply
     *
     * @return
     */
    @RequestMapping( value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<PostCommentReply> list  = postCommentReplyService.getAll();
        return BaseResult.ok(list);
    }

    /**
     * 保存攻略图片
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String postCommentReplyJson) {
        int result = 0;

        PostCommentReply postCommentReply = null;
        try {
            postCommentReply = MapperUtils.json2pojo(postCommentReplyJson, PostCommentReply.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (postCommentReply != null) {

            result = postCommentReplyService.insert(postCommentReply);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 根据ID获取postCommentReply
     *
     * @param postCommentReplyGuid
     * @return
     */
    @RequestMapping(value = "{postCommentReplyGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postCommentReplyGuid") String postCommentReplyGuid) {
        PostCommentReply postCommentReply = new PostCommentReply();
        postCommentReply.setId(Integer.valueOf(postCommentReplyGuid));
        PostCommentReply obj = postCommentReplyService.selectOne(postCommentReply);

        return BaseResult.ok(obj);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param postCommentReplyJson
     * @return
     */
    @ApiOperation(value = "分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "postCommentJson", value = "对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true) int pageNum,
            @PathVariable(required = true) int pageSize,
            @RequestParam(required = false) String postCommentReplyJson
    ) {
        PostCommentReply postCommentReply = null;
        try {
            postCommentReply = MapperUtils.json2pojo(postCommentReplyJson, PostCommentReply.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(postCommentReply);
        PageInfo pageInfo = postCommentReplyService.page(pageNum, pageSize, postCommentReply);
        List<PostCommentReply> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除
     *
     * @param postCommentReplyGuid
     * @return
     */
    @RequestMapping(value = "{postCommentReplyGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "postCommentReplyGuid") String postCommentReplyGuid) {

        PostCommentReply postCommentReply = new PostCommentReply();
        postCommentReply.setId(Integer.valueOf(postCommentReplyGuid));
        int result = 0;

        result = postCommentReplyService.delete(postCommentReply);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新
     *
     * @param postCommentReplyJson
     * @return
     */
    @RequestMapping(value = "{postCommentReplyGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "postCommentReplyGuid") String postCommentReplyGuid, @RequestParam(required = true) String postCommentReplyJson) {
        int result = 0;

        PostCommentReply postCommentReply = null;
        try {
            postCommentReply = MapperUtils.json2pojo(postCommentReplyJson, PostCommentReply.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = postCommentReplyService.update(postCommentReply);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    
}
