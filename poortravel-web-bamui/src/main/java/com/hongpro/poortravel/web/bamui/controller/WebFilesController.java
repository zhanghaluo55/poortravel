package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.Files;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.FilesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/files")
public class WebFilesController extends BaseController<Files, FilesService> {

    @Autowired
    private FilesService filesService;

    @ModelAttribute
    public Files files(String id) {
        Files files = null;

        if (StringUtils.isBlank(id)) {
            files = new Files();
        } else {
            String json = filesService.get(id);
            try {
                files = MapperUtils.json2pojo(json, Files.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (files == null) {
                files = new Files();
            }
        }


        return files;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String save(Files files, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        String fileJson = MapperUtils.obj2json(files);
        String json = filesService.save(fileJson);
        System.out.println("json:"+json);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/files/getall";
        }
        return "redirect:/v1/files/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String fileJson, RedirectAttributes redirectAttributes) {
        String json = filesService.page(pageNum, pageSize, fileJson);
        System.out.println("json" + json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/fileslist";
    }

    @ResponseBody
    @RequestMapping(value = "{fileGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "fileGuid") String fileGuid) {
        return filesService.get(fileGuid);
    }

    @RequestMapping(value = "{fileGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "fileGuid") String fileGuid, RedirectAttributes redirectAttributes) {
        filesService.delete(fileGuid);
        return "redirect:/v1/files/getall";
    }

    @RequestMapping(value = "{fileGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "fileGuid") String fileGuid, Files files) {
        String json =null;
        try {
           json = MapperUtils.obj2json(files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(json);
        filesService.update(fileGuid,json);
        return "redirect:/v1/files/getall";
    }

    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(RedirectAttributes redirectAttributes) {
        String json = filesService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/fileslist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=filesService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/fileslist";
    }
}

