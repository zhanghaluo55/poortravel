package com.hongpro.poortravel.service.attraction.service.consumer;

import com.hongpro.poortravel.service.attraction.service.consumer.fallback.FilesServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-base", fallback = FilesServiceFallback.class)
public interface FilesService{

    @RequestMapping(value = "v1/files/{filesGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "filesGuid") String filesGuid);

    @RequestMapping(value = "v1/files", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/files/some", method = RequestMethod.GET)
    public String getSome(@RequestParam(required = true) String attrfileslist);

}
