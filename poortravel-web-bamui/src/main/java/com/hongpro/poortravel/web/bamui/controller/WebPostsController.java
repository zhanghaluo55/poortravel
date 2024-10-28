package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "v1/posts")
public class WebPostsController extends BaseController<Post, PostsService> {

    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public Post post() {

//        if (StringUtils.isBlank(id)) {
//            post = new Post();
//        } else {
//            String json = postsService.get(id);
//            try {
//                post = MapperUtils.json2pojo(json, Post.class);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if (post == null) {
//                post = new Post();
//            }
//        }

        return new Post();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Post post, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        post.setPostDate(new Date());
        post.setUpdateDate(new Date());
        String postJson = MapperUtils.obj2json(post);
        String json = postsService.save(postJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/posts/getall";
        }
        return "redirect:/v1/posts/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String postJson, RedirectAttributes redirectAttributes) {
        String json = postsService.page(pageNum, pageSize, postJson);
        System.out.println("json" + json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/postlist";
    }
    @ResponseBody
    @RequestMapping(value = "{postGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "postGuid") String postGuid) {
        return postsService.get(postGuid);
    }

    @RequestMapping(value = "{postGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "postGuid") String postGuid, RedirectAttributes redirectAttributes) {
        postsService.delete(postGuid);
        return "redirect:/v1/posts/getall";
    }

    @RequestMapping(value = "{postGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "postGuid") String postGuid,Post post) throws Exception {
        String  json = MapperUtils.obj2json(post);

        System.out.println("json:"+json);
        postsService.update(postGuid,json);
        return "redirect:/v1/posts/getall";
    }

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(RedirectAttributes redirectAttributes) {
        String json = postsService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/postlist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=postsService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/postlist";
    }
}

