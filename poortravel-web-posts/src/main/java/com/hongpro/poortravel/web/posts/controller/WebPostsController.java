package com.hongpro.poortravel.web.posts.controller;

import com.hongpro.poortravel.common.domain.Post;
import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.posts.service.PostsService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebPostsController extends BaseController<Post, PostsService> {

    @Autowired
    private PostsService postsService;

    @ModelAttribute
    public Post post(int id) {
        Post post = null;

        if (StringUtils.isBlank(String.valueOf(id))) {
            post = new Post();
        } else {
            String json = postsService.get(id);
            try {
                post = MapperUtils.json2pojo(json, Post.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (post == null) {
                post = new Post();
            }
        }


        return post;
    }

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = {"", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(Post post, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String postsJson = MapperUtils.obj2json(post);
        String json = postsService.save(postsJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect;/index";
        }
        return "redirect:/ form";
    }

}

