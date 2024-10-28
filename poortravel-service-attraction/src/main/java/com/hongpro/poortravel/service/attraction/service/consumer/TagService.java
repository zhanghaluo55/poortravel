package com.hongpro.poortravel.service.attraction.service.consumer;

import com.hongpro.poortravel.service.attraction.service.consumer.fallback.TagServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(value = "poortravel-service-base", fallback = TagServiceFallback.class)
public interface TagService{

    @RequestMapping(value = "v1/tags/{tagGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "tagGuid") String tagGuid);

    @RequestMapping(value = "v1/tags", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/tags/some", method = RequestMethod.GET)
    public String getSome(@RequestParam(required = true) String attrtaglist);

}
