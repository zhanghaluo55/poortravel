package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.PostFilesServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-posts", fallback = PostFilesServiceFallback.class)
public interface PostFilesService extends BaseClientService {
    @RequestMapping(value = "v1/postfiles/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "postFilesJson") String postFilesJson);

    @RequestMapping(value = "v1/postfiles/{postFilesGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "postFilesGuid") String postFilesGuid);

    @RequestMapping(value = "v1/postfiles", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "postFilesJson") String postFilesJson);

    @RequestMapping(value = "v1/postfiles/{postFilesGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "postFilesGuid") String postFilesGuid);

    @RequestMapping(value = "v1/postfiles/{postFilesGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "postFilesGuid") String postFilesGuid, @RequestParam(required = true, value = "postFilesJson") String postFilesJson);

    @RequestMapping(value = "v1/postfiles/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/postfiles/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
