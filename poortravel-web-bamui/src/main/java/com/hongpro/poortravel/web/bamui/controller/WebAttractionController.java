package com.hongpro.poortravel.web.bamui.controller;

import com.hongpro.poortravel.common.domain.Attraction;
import com.hongpro.poortravel.common.domain.Files;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.common.web.controller.BaseController;
import com.hongpro.poortravel.web.bamui.service.AttractionService;
import com.hongpro.poortravel.web.bamui.service.FilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "v1/attractions")
public class WebAttractionController extends BaseController<Attraction, AttractionService> {

    @Autowired
    private AttractionService attractionService;
    @Autowired
    private FilesService filesService;

   /* @ModelAttribute
    public Attraction attraction(String id) {
        Attraction attraction = null;

        if (StringUtils.isBlank(id)) {
            attraction = new Attraction();
        } else {
            String json = attractionService.getOne(id);
            try {
                attraction = MapperUtils.json2pojo(json, Attraction.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (attraction == null) {
                attraction = new Attraction();
            }
        }
        return attraction;
    }*/

    @RequestMapping(method = RequestMethod.POST)
    public String save(Attraction attraction, HttpServletRequest request, RedirectAttributes redirectAttributes) throws Exception {
        attraction.setStatus(1);
        attraction.setLikes(0);
        attraction.setCollect(0);
        if(attraction.getViewImage()!=null){
            Files files=new Files();
            String ViewImage=attraction.getViewImage();
            //String filename=ViewImage.substring(ViewImage.lastIndexOf("/")+1,ViewImage.lastIndexOf("."));
            String filename=attraction.getTitle();
            String type=ViewImage.substring(ViewImage.lastIndexOf(".")+1,ViewImage.length());
            files.setFilename(filename);
            files.setFiletype(type);
            files.setPath(ViewImage);
            String filesJson = MapperUtils.obj2json(files);
            filesService.save(filesJson);
        }
        String attractionJson = MapperUtils.obj2json(attraction);

        String json = attractionService.save(attractionJson);
        BaseResult baseResult = MapperUtils.json2pojo(json, BaseResult.class);

        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        if (baseResult.getSuccess().endsWith("成功")) {
            return "redirect:/v1/attractions/getall";
        }
        return "redirect:/v1/attractions/getall";
    }

    /**
     * 查找攻略列表页（分页）
     *
     * @return
     */
    @RequestMapping(value = "page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false) String attractionJson, RedirectAttributes redirectAttributes) {
        String json = attractionService.page(pageNum, pageSize, attractionJson);
        System.out.println("attrjson:"+json);
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        System.out.println("result:"+baseResult);
        return "redirect:/attractionlist";
    }
    @ResponseBody
    @RequestMapping(value = "{attractionGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(value = "attractionGuid") String attractionGuid) {
        return attractionService.getOne(attractionGuid);
    }

    @RequestMapping(value = "{attractionGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "attractionGuid") String attractionGuid, RedirectAttributes redirectAttributes) {
        attractionService.delete(attractionGuid);
        return "redirect:/v1/attractions/getall";
    }

    @RequestMapping(value = "{attractionGuid}",method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionGuid") String attractionGuid,Attraction attraction) throws Exception {
        System.out.println("address:"+attraction.getAddress());
        String attractionJson = MapperUtils.obj2json(attraction);
        System.out.println("json:"+attractionJson);
        String lastAttractionJson=attractionService.getOne(attraction.getId().toString());
        BaseResult baseResult = MapperUtils.json2pojo(lastAttractionJson,BaseResult.class);
        String json = MapperUtils.obj2json(baseResult.getData());
        Attraction lastAttraction=MapperUtils.json2pojo(json,Attraction.class);

        if(!lastAttraction.getViewImage().equals(attraction.getViewImage())){
            Files files=new Files();
            String ViewImage=attraction.getViewImage();
            String filename=ViewImage.substring(ViewImage.lastIndexOf("/")+1,ViewImage.lastIndexOf("."));
            String type=ViewImage.substring(ViewImage.lastIndexOf(".")+1,ViewImage.length());
            files.setFilename(filename);
            files.setPath(ViewImage);
            files.setFiletype(type);
            String filesJson = MapperUtils.obj2json(files);
            filesService.save(filesJson);
        }
        attractionService.update(attractionGuid, attractionJson);
        return "redirect:/v1/attractions/getall";
    }

    /**
     * 查找攻略
     *
     * @return
     */
    @RequestMapping(value = "getall", method = RequestMethod.GET)
    public String getAll(@RequestParam(required = false) String attractionJson, RedirectAttributes redirectAttributes) throws Exception {
        String json = attractionService.getAll();
        BaseResult baseResult = null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);
        return "redirect:/attractionlist";
    }

    @RequestMapping(value = "search",method = RequestMethod.GET)
    public String search(@RequestParam(required = false) String key, RedirectAttributes redirectAttributes){
        String json=attractionService.search(key);
        BaseResult baseResult =null;
        try {
            baseResult = MapperUtils.json2pojo(json, BaseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        redirectAttributes.addFlashAttribute("baseResult", baseResult);

        return "redirect:/attractionlist";
    }
}

