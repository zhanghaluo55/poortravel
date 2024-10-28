package com.hongpro.poortravel.web.admin.controller;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.admin.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebAdminController extends BaseController<User, AdminService> {

    @Autowired
    private AdminService adminService;

    @ModelAttribute
    public User user(int id) {
        User user = null;

        if (StringUtils.isBlank(String.valueOf(id))) {
            user = new User();
        } else {
            String json = adminService.get(id);
            try {
                user = MapperUtils.json2pojo(json, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (user == null) {
                user = new User();
            }
        }
        return user;
    }

    /*@RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        String json=adminService.login("","");
        System.out.println(json);
        return "index";
    }*/
    @RequestMapping(value = {"index.html", "index"}, method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "form";
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User post, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String usersJson = MapperUtils.obj2json(post);
        String json = adminService.save(usersJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect;/index";
        }
        return "redirect:/ form";
    }
}
