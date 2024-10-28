package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.PostFiles;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.PostFilesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/postfiles")
public class WebPostFilesController extends BaseController<PostFiles, PostFilesService> {

    @Autowired
    private PostFilesService postFilesService;

    @ModelAttribute
    public PostFiles postFiles(String id) {
        PostFiles postFiles = null;

        if (StringUtils.isBlank(id)) {
            postFiles = new PostFiles();
        } else {
            String json = postFilesService.get(id);
            try {
                postFiles = MapperUtils.json2pojo(json, PostFiles.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (postFiles == null) {
                postFiles = new PostFiles();
            }
        }
        return postFiles;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(PostFiles postFiles, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String postFilesJson = MapperUtils.obj2json(postFiles);

        String json = postFilesService.save(postFilesJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/postfiles/getall";
        }
        return "redirect:/v1/postfiles/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String postFilesJson, RedirectAttributes redirectAttributes) {
        String json = postFilesService.page(pageNum, pageSize, postFilesJson);
        System.out.println("postjson:"+json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        System.out.println("result:"+baseResult);
        return "redirect:/postfileslist";
    }
    @ResponseBody
    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "postFilesGuid") String postFilesGuid) {
        return postFilesService.get(postFilesGuid);
    }

    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "postFilesGuid") String postFilesGuid, RedirectAttributes redirectAttributes) {
        postFilesService.delete(postFilesGuid);
        return "redirect:/v1/attrfiles/getall";
    }

    @RequestMapping(value = "{postFilesGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "postFilesGuid") String postFilesGuid, @RequestParam(required = false) PostFiles postFiles) throws Exception {
        String postFilesJson = MapperUtils.obj2json(postFiles);

        postFilesService.update(postFilesGuid,postFilesJson);
        return "redirect:/v1/postfiles/getall";
    }

    /**
     * 查找攻略
     *
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(@RequestParam(required = false) String postFilesJson, RedirectAttributes redirectAttributes) throws Exception {
        String json = postFilesService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/postfileslist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=postFilesService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/postfileslist";
    }
}

