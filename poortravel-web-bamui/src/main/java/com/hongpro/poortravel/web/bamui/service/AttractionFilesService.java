package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.AttractionFilesServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-attraction", fallback = AttractionFilesServiceFallback.class)
public interface AttractionFilesService extends BaseClientService {
    @RequestMapping(value = "v1/attractionfiles/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "attractionFilesJson") String attractionFilesJson);

    @RequestMapping(value = "v1/attractionfiles/{attractionFilesGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "attractionFilesGuid") String attractionFilesGuid);

    @RequestMapping(value = "v1/attractionfiles", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "attractionFilesJson") String attractionFilesJson);

    @RequestMapping(value = "v1/attractionfiles/{attractionFilesGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "attractionFilesGuid") String attractionFilesGuid);

    @RequestMapping(value = "v1/attractionfiles/{attractionFilesGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionFilesGuid") String attractionFilesGuid, @RequestParam(required = true, value = "attractionFilesJson") String attractionFilesJson);

    @RequestMapping(value = "v1/attractionfiles/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/attractionfiles/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
