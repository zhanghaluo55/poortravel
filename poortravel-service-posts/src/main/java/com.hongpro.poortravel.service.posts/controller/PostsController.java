package com.hongpro.poortravel.service.posts.controller;

import com.github.pagehelper.PageInfo;
import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.domain.UserPostcollect;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.service.posts.service.PostsService;
import com.hongpro.poortravel.service.posts.service.consumer.UserPostcollectService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/v1/posts")
public class PostsController {
    @Autowired
    private PostsService<Post> postsService;
    @Autowired
    private UserPostcollectService userPostcollectService;
    /**
     * 根据ID获取posts
     *
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(value = "postGuid") String postGuid) {
        Post post = new Post();
        post.setId(Integer.valueOf(postGuid));
        Post obj = postsService.selectOne(post);

        obj.setShareNum(obj.getShareNum()+1);
        postsService.update(obj);
        return BaseResult.ok(obj);
    }

    /**
     * 保存文章
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public BaseResult save(@RequestParam(required = true) String postJson) {
        int result = 0;

        Post post = null;
        try {
            post = MapperUtils.json2pojo(postJson, Post.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        post.setLikes(0);
        post.setPostDate(new Date());
        post.setUpdateDate(new Date());
        post.setShareNum(0);
        post.setCommentNum(0);
        post.setStatus(1);
        if (post != null) {
            //新增文章
            result = postsService.insert(post);
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
     * @param postJson
     * @return
     */
    @ApiOperation(value = "文章分页查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "pageSize", value = "笔数", required = true, dataType = "int", paramType = "path"),
            @ApiImplicitParam(name = "postJson", value = "文章对象 JSON 字符串", required = false, dataTypeClass = String.class, paramType = "json")
    })
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public BaseResult page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "postJson") String postJson
    ) {
        System.out.println("flag");
        System.out.println("pageNum:" + pageNum);
        System.out.println("pageSize:" + pageSize);
        Post post = null;
        try {
            post = MapperUtils.json2pojo(postJson, Post.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(post);
        PageInfo pageInfo = postsService.page(pageNum, pageSize, post);
        List<Post> list = pageInfo.getList();
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
     * @param postGuid
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.DELETE)
    public BaseResult delete(@PathVariable(value = "postGuid") String postGuid) {

        Post post = new Post();
        post.setId(Integer.valueOf(postGuid));
        int result = 0;

        result = postsService.delete(post);

        if (result > 0) {
            return BaseResult.ok("删除成功");
        }
        return BaseResult.ok("删除失败");
    }


    /**
     * 根据id更新文章
     *
     * @param postJson
     * @return
     */
    @RequestMapping(value = "{postGuid}", method = RequestMethod.PUT)
    public BaseResult update(@PathVariable(value = "postGuid") String postGuid, @RequestParam(required = true) String postJson) {
        int result = 0;
        System.out.println("json:"+postJson);
        Post post = null;
        try {
            post = MapperUtils.json2pojo(postJson, Post.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = postsService.update(post);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    /**
     * 获取所有posts
     * @param
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public BaseResult getAll() {
        List<Post> list = postsService.getAll();

        return BaseResult.ok(list);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public BaseResult search(@RequestParam(required = true) String key) {
        List<Post> list=postsService.search(key);

        return BaseResult.ok(list);
    }

    /**
     * 根据省份获取所有post
     *
     * @param
     * @return
     */
    @RequestMapping(value = "searchprovince", method = RequestMethod.GET)
    public BaseResult searchByProvince(@RequestParam(required = false) String province ){
        System.out.println(province);
        List<Post> posts = postsService.selectByProvince(province);

        return BaseResult.ok(posts);
    }

    @RequestMapping(value = "collect/{userGuid}", method = RequestMethod.GET)
    public BaseResult selectCollect(@PathVariable(value = "userGuid") String userGuid) throws Exception {
        String json=userPostcollectService.getUserPostcollectList(userGuid);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        String json2=MapperUtils.obj2json(baseResult.getData());
        List<UserPostcollect> userPostcollects = MapperUtils.json2list(json2, UserPostcollect.class);
        List<Post> result = new ArrayList<>();
        Post temp = new Post();
        for (UserPostcollect userPostcollect: userPostcollects){
            temp.setId(userPostcollect.getPid());
            result.add(postsService.selectOne(temp));
        }

        return BaseResult.ok(result);
    }

    @RequestMapping(value = "userposts/{userGuid}", method = RequestMethod.GET)
    public BaseResult selectUsersPost(@PathVariable(value = "userGuid") String userGuid) throws Exception {

        List<Post> result = postsService.selectByUid(userGuid);
        return BaseResult.ok(result);
    }

    @RequestMapping(value = "likecount/{postGuid}", method = RequestMethod.PUT)
    public BaseResult countLike(@PathVariable(value = "postGuid") String postGuid) throws Exception {

        Post post =new Post();
        post.setId(Integer.valueOf(postGuid));
        Post post1 = postsService.selectOne(post);
        post1.setCommentNum(post1.getCommentNum()+1);

        int result = 0;
        result = postsService.update(post1);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }

    @RequestMapping(value = "collectcount/{postGuid}", method = RequestMethod.GET)
    public BaseResult countCollect(@PathVariable(value = "postGuid") String postGuid) throws Exception {

        Post post =new Post();
        post.setId(Integer.valueOf(postGuid));
        Post post1 = postsService.selectOne(post);
        post1.setLikes(post1.getLikes()+1);

        int result = 0;
        result = postsService.update(post1);
        if (result > 0) {
            return BaseResult.ok("更新成功");
        }
        return BaseResult.ok("更新失败");
    }
}
