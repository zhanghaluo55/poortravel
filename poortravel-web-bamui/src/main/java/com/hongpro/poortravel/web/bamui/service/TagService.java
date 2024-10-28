package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.TagServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-base", fallback = TagServiceFallback.class)
public interface TagService extends BaseClientService {
    @RequestMapping(value = "v1/tags/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "tagJson") String tagJson);

    @RequestMapping(value = "v1/tags/{tagGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "tagGuid") String tagGuid);

    @RequestMapping(value = "v1/tags", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "tagJson") String tagJson);

    @RequestMapping(value = "v1/tags/{tagGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "tagGuid") String tagGuid);

    @RequestMapping(value = "v1/tags/{tagGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "tagGuid") String tagGuid, @RequestParam(required = true, value = "tagJson") String tagJson);

    @RequestMapping(value = "v1/tags/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/tags/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
