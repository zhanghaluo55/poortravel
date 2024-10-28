package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.PostsServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-posts", fallback = PostsServiceFallback.class)
public interface PostsService extends BaseClientService {
    @RequestMapping(value = "v1/posts/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "postJson") String postJson);

    @RequestMapping(value = "v1/posts/{postGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "postGuid") String postGuid);

    @RequestMapping(value = "v1/posts", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "postJson") String postJson);

    @RequestMapping(value = "v1/posts/{postGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "postGuid") String postGuid);

    @RequestMapping(value = "v1/posts/{postGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "postGuid") String postGuid, @RequestParam(required = true, value = "postJson") String postJson);

    @RequestMapping(value = "v1/posts/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/posts/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
