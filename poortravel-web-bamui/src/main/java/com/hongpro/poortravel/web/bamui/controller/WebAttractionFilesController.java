package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.AttractionFiles;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.AttractionFilesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/attractionfiles")
public class WebAttractionFilesController extends BaseController<AttractionFiles, AttractionFilesService> {

    @Autowired
    private AttractionFilesService attractionFilesService;

    @ModelAttribute
    public AttractionFiles attractionFiles(String id) {
        AttractionFiles attractionFiles = null;

        if (StringUtils.isBlank(id)) {
            attractionFiles = new AttractionFiles();
        } else {
            String json = attractionFilesService.get(id);
            try {
                attractionFiles = MapperUtils.json2pojo(json, AttractionFiles.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (attractionFiles == null) {
                attractionFiles = new AttractionFiles();
            }
        }
        return attractionFiles;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(AttractionFiles attractionFiles, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String attractionFilesJson = MapperUtils.obj2json(attractionFiles);

        String json = attractionFilesService.save(attractionFilesJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/attractionfiles/getall";
        }
        return "redirect:/v1/attractionfiles/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String attractionFilesJson, RedirectAttributes redirectAttributes) {
        String json = attractionFilesService.page(pageNum, pageSize, attractionFilesJson);
        System.out.println("attrjson:"+json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        System.out.println("result:"+baseResult);
        return "redirect:/attrfileslist";
    }
    @ResponseBody
    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid) {
        return attractionFilesService.get(attractionFilesGuid);
    }

    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid, RedirectAttributes redirectAttributes) {
        attractionFilesService.delete(attractionFilesGuid);
        return "redirect:/v1/attrfiles/getall";
    }

    @RequestMapping(value = "{attractionFilesGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid, @RequestParam(required = false) AttractionFiles attractionFiles) throws Exception {
        String attractionFilesJson = MapperUtils.obj2json(attractionFiles);

        attractionFilesService.update(attractionFilesGuid, attractionFilesJson);
        return "redirect:/v1/attractionfiles/getall";
    }

    /**
     * 查找攻略
     *
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(@RequestParam(required = false) String attractionFilesJson, RedirectAttributes redirectAttributes) throws Exception {
        String json = attractionFilesService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/attrfileslist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=attractionFilesService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/attrfileslist";
    }
}

