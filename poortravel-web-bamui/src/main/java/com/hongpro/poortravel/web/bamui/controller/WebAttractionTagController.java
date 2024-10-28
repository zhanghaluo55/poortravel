package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.AttractionTag;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.AttractionTagService;
import com.hongpro.poortravel.web.bamui.service.FilesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/attractiontags")
public class WebAttractionTagController extends BaseController<AttractionTag, AttractionTagService> {

    @Autowired
    private AttractionTagService attractionTagService;
    @Autowired
    private FilesService filesService;

    @ModelAttribute
    public AttractionTag attractionTag(String id) {
        AttractionTag attractionTag = null;

        if (StringUtils.isBlank(id)) {
            attractionTag = new AttractionTag();
        } else {
            String json = attractionTagService.get(id);
            try {
                attractionTag = MapperUtils.json2pojo(json, AttractionTag.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (attractionTag == null) {
                attractionTag = new AttractionTag();
            }
        }
        return attractionTag;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(AttractionTag attractionTag, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String attractionTagJson = MapperUtils.obj2json(attractionTag);

        String json = attractionTagService.save(attractionTagJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/attractiontags/getall";
        }
        return "redirect:/v1/attractiontags/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String attractionTagJson, RedirectAttributes redirectAttributes) {
        String json = attractionTagService.page(pageNum, pageSize, attractionTagJson);
        System.out.println("attrjson:"+json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        System.out.println("result:"+baseResult);
        return "redirect:/attrtaglist";
    }
    @ResponseBody
    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "attractionTagGuid") String attractionTagGuid) {
        return attractionTagService.get(attractionTagGuid);
    }

    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "attractionTagGuid") String attractionTagGuid, RedirectAttributes redirectAttributes) {
        attractionTagService.delete(attractionTagGuid);
        return "redirect:/v1/attractiontags/getall";
    }

    @RequestMapping(value = "{attractionTagGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionTagGuid") String attractionTagGuid, @RequestParam(required = false) AttractionTag attractionTag) throws Exception {
        String attractionTagJson = MapperUtils.obj2json(attractionTag);
        attractionTagService.update(attractionTagGuid,attractionTagJson);
        return "redirect:/v1/attractiontags/getall";
    }

    /**
     * 查找攻略
     *
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(@RequestParam(required = false) String attractionTagJson, RedirectAttributes redirectAttributes) throws Exception {
        String json = attractionTagService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/attrtaglist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=attractionTagService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/attrtaglist";
    }
}

