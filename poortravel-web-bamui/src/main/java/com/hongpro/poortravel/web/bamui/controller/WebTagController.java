package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.Tag;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.TagService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/tags")
public class WebTagController extends BaseController<Tag, TagService> {

    @Autowired
    private TagService tagService;

    @ModelAttribute
    public Tag tag(String id) {
        Tag tag = null;

        if (StringUtils.isBlank(id)) {
            tag = new Tag();
        } else {
            String json = tagService.get(id);
            try {
                tag = MapperUtils.json2pojo(json, Tag.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (tag == null) {
                tag = new Tag();
            }
        }


        return tag;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Tag tag, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String tagJson = MapperUtils.obj2json(tag);
        String json = tagService.save(tagJson);
        System.out.println("json:"+json);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/tags/getall";
        }
        return "redirect:/v1/tags/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String tagJson, RedirectAttributes redirectAttributes) {
        String json = tagService.page(pageNum, pageSize, tagJson);
        System.out.println("json" + json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/taglist";
    }

    @ResponseBody
    @RequestMapping(value = "{tagGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "tagGuid") String tagGuid) {
        return tagService.get(tagGuid);
    }

    @RequestMapping(value = "{tagGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "tagGuid") String tagGuid, RedirectAttributes redirectAttributes) {
        tagService.delete(tagGuid);
        return "redirect:/v1/tags/getall";
    }

    @RequestMapping(value = "{tagGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "tagGuid") String tagGuid, Tag tag) {
        String json =null;
        try {
           json = MapperUtils.obj2json(tag);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        tagService.update(tagGuid,json);
        return "redirect:/v1/tag/getall";
    }

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(RedirectAttributes redirectAttributes) {
        String json = tagService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/taglist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=tagService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/taglist";
    }
}

