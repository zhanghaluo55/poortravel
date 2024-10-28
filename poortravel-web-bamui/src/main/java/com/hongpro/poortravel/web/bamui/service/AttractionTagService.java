package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.AttractionTagServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-attraction", fallback = AttractionTagServiceFallback.class)
public interface AttractionTagService extends BaseClientService {
    @RequestMapping(value = "v1/attractiontags/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "attractionTagJson") String attractionTagJson);

    @RequestMapping(value = "v1/attractiontags/{attractiontagGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "attractionTagGuid") String attractionTagGuid);

    @RequestMapping(value = "v1/attractiontags", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "attractionTagJson") String attractionTagJson);

    @RequestMapping(value = "v1/attractiontags/{attractionTagGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "attractionTagGuid") String attractionTagGuid);

    @RequestMapping(value = "v1/attractiontags/{attractionTagGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "attractionTagGuid") String attractionTagGuid, @RequestParam(required = true, value = "attractionTagJson") String attractionTagJson);

    @RequestMapping(value = "v1/attractiontags/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/attractiontags/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
