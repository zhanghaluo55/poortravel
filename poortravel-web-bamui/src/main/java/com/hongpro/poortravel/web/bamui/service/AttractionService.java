package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.AttractionServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-attraction", fallback = AttractionServiceFallback.class)
public interface AttractionService extends BaseClientService {
    @RequestMapping(value = "v1/attractions/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "attractionJson") String attractionJson);

    @RequestMapping(value = "v1/attractions/get/{attractionGuid}", method = RequestMethod.GET)
    public String getOne(@PathVariable(required = true, value = "attractionGuid") String attractionGuid);

    @RequestMapping(value = "v1/attractions", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "attractionJson") String attractionJson);

    @RequestMapping(value = "v1/attractions/{attractionGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "attractionGuid") String attractionGuid);

    @RequestMapping(value = "v1/attractions/{attractionGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionGuid") String attractionGuid, @RequestParam(required = true, value = "attractionJson") String attractionJson);

    @RequestMapping(value = "v1/attractions/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/attractions/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
