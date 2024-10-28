package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.User;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@RequestMapping(value = "v1/admins")
public class WebAdminController extends BaseController<User, AdminService> {

    @Autowired
    private AdminService adminService;

    @Value("${hosts.bamui}")
    private String hosts_bamui;
    @ModelAttribute
    public User user(String id) {
        User user = null;

        if (StringUtils.isBlank(id)) {
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


    /**
     * 查找用户列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String userJson, RedirectAttributes redirectAttributes) {
        String json = adminService.page(pageNum, pageSize, userJson);
        System.out.println("json" + json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
//        return "redirect:userlist";
        System.out.println(baseResult);
        return "redirect:/userlist";
    }


    /**
     * 保存用户
     *
     * @param user
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.POST)
    public String save(User user, RedirectAttributes redirectAttributes) throws Exception {
        user.setRegDate(new Date());
        user.setStatus(1);
        String userJson = MapperUtils.obj2json(user);
        String json = adminService.save(userJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/admins/getall";
        }
        return "redirect:/v1/admins/getall";
    }
    @ResponseBody
    @RequestMapping(value = "{userGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "userGuid") String userGuid) {
        return adminService.get(userGuid);
    }

    @RequestMapping(value = "{userGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "userGuid") String userGuid, RedirectAttributes redirectAttributes) {
        adminService.delete(userGuid);
        return "redirect:/v1/admins/getall";
    }

    @RequestMapping(value = "{userGuid}",method = RequestMethod.PUT)
    public String update(@PathVariable(value = "userGuid") String userGuid,User user) {
        String json = null ;
        try {
            json = MapperUtils.obj2json(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        adminService.update(userGuid,json);
        return "redirect:/v1/admins/getall";
    }

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(RedirectAttributes redirectAttributes) {
        String json = adminService.getAll();
        System.out.println("json"+json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/userlist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=adminService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/userlist";
    }
}
