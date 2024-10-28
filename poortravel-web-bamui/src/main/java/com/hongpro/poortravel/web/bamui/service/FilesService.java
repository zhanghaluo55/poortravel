package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.FilesServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-base", fallback = FilesServiceFallback.class)
public interface FilesService extends BaseClientService {
    @RequestMapping(value = "v1/files/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(
            @PathVariable(required = true, value = "pageNum") int pageNum,
            @PathVariable(required = true, value = "pageSize") int pageSize,
            @RequestParam(required = false, value = "fileJson") String fileJson);

    @RequestMapping(value = "v1/files/{fileGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "fileGuid") String fileGuid);

    @RequestMapping(value = "v1/files", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "fileJson") String fileJson);

    @RequestMapping(value = "v1/files/{fileGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "fileGuid") String fileGuid);

    @RequestMapping(value = "v1/files/{fileGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "fileGuid") String fileGuid, @RequestParam(required = true, value = "fileJson") String fileJson);

    @RequestMapping(value = "v1/files/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/files/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
