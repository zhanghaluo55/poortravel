package com.hongpro.poortravel.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.PostComment;
import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.posts.service.PostCommentService;
import com.hongpro.poortravel.service.posts.service.consumer.AdminService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/v1/postcomments")
public class PostCommentController {
    @Autowired
    private PostCommentService<PostComment> postCommentService;
    @Autowired
    private AdminService adminService;

    /**
     * 获取所有postComment
     *
     * @return
     */
    @RequestMapping( value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<PostComment> list  = postCommentService.getAll();
        return BaseResult.ok(list);
    }

    /**
     * 保存攻略图片
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String postCommentJson) {
        int result = 0;
        System.out.println("commentJson:"+postCommentJson);
        PostComment postComment = null;
        try {
            postComment = MapperUtils.json2pojo(postCommentJson, PostComment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (postComment != null) {

            result = postCommentService.insert(postComment);
            if (result > 0) {
                return BaseResult.ok("保存成功");
            }
        }
        return BaseResult.ok("保存失败");
    }

    /**
     * 根据ID获取postComment
     *
     * @param postCommentGuid
     * @return
     */
    @RequestMapping(value = "{postCommentGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postCommentGuid") String postCommentGuid) throws Exception {
        PostComment postComment = new PostComment();
        postComment.setId(Integer.valueOf(postCommentGuid));
        PostComment obj = postCommentService.selectOne(postComment);

        String json = adminService.get(obj.getUid().toString());
        BaseResult baseResult = MapperUtils.json2pojo(json,BaseResult.class);
        String json2 = MapperUtils.obj2json(baseResult.getData());
        User user = MapperUtils.json2pojo(json2, User.class);

        obj.setUser((user));

        return BaseResult.ok(obj);
    }

    /**
     * 分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param postCommentJson
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
            @RequestParam(required = false) String postCommentJson
    ) {
        PostComment postComment = null;
        try {
            postComment = MapperUtils.json2pojo(postCommentJson, PostComment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(postComment);
        PageInfo pageInfo = postCommentService.page(pageNum, pageSize, postComment);
        List<PostComment> list = pageInfo.getList();

        BaseResult.Cursor cursor = new BaseResult.Cursor();
        cursor.setTotal(new Long(pageInfo.getTotal()).intValue());
        cursor.setOffset(pageInfo.getPageNum());
        cursor.setLimit(pageInfo.getPageSize());
        return BaseResult.ok(list, cursor);
    }


    /**
     * 根据id删除
     *
     * @param postCommentGuid
     * @return
     */
    @RequestMapping(value = "{postCommentGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "postCommentGuid") String postCommentGuid) {

        PostComment postComment = new PostComment();
        postComment.setId(Integer.valueOf(postCommentGuid));
        int result = 0;

        result = postCommentService.delete(postComment);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新
     *
     * @param postCommentJson
     * @return
     */
    @RequestMapping(value = "{postCommentGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "postCommentGuid") String postCommentGuid, @RequestParam(required = true) String postCommentJson) {
        int result = 0;

        PostComment postComment = null;
        try {
            postComment = MapperUtils.json2pojo(postCommentJson, PostComment.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = postCommentService.update(postComment);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    @RequestMapping(value = "list/{postGuid}", method = RequestMethod.GET)
    public BaseResult getAttrFilesList(@PathVariable(value = "postGuid") String postGuid) throws Exception {

        List<PostComment> obj = postCommentService.selectListPostComment(postGuid);
        List<PostComment> result=new ArrayList<>();
        for (PostComment postComment: obj){
            String json = adminService.get(postComment.getUid().toString());
            BaseResult baseResult = MapperUtils.json2pojo(json,BaseResult.class);
            String json1=MapperUtils.obj2json(baseResult.getData());
            User user = MapperUtils.json2pojo(json1,User.class);

            postComment.setUser(user);
            result.add(postComment);
        }
        return BaseResult.ok(result);
    }

    @RequestMapping(value = "like/{postCommentGuid}", method = RequestMethod.GET)
    public BaseResult addlikes(@PathVariable(value = "postCommentGuid") String postCommentGuid) throws Exception {
        PostComment postComment = new PostComment();
        postComment.setId(Integer.valueOf(postCommentGuid));
        PostComment tmp =postCommentService.selectOne(postComment);
        PostComment t= new PostComment();
        t.setLikes(tmp.getLikes()+1);

        int result = postCommentService.update(t);
        if(result>0){
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }


}
