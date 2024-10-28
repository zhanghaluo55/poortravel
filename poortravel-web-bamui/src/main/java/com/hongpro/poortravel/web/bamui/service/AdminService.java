package com.hongpro.poortravel.web.bamui.service;

import com.hongpro.poortravel.common.web.service.BaseClientService;
import com.hongpro.poortravel.web.bamui.service.fallback.AdminServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "poortravel-service-admin", fallback = AdminServiceFallback.class)
public interface AdminService extends BaseClientService {
    @RequestMapping(value = "v1/admins/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password);

    @RequestMapping(value = "v1/admins/{userGuid}", method = RequestMethod.GET)
    public String get(@PathVariable(required = true, value = "userGuid") String userGuid);

    @RequestMapping(value = "v1/admins/page/{pageNum}/{pageSize}", method = RequestMethod.GET)
    public String page(@PathVariable(required = true, value = "pageNum") int pageNum,
                       @PathVariable(required = true, value = "pageSize") int pageSize,
                       @RequestParam(required = false, value = "userJson") String userJson);

    @RequestMapping(value = "v1/admins", method = RequestMethod.POST)
    public String save(@RequestParam(required = true, value = "userJson") String userJson);

    @RequestMapping(value = "v1/admins/{userGuid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(required = true, value = "userGuid") String userGuid);

    @RequestMapping(value = "v1/admins/{userGuid}", method = RequestMethod.PUT)
    public String update(@PathVariable(value = "userGuid") String userGuid, @RequestParam(required = true, value = "userJson") String userJson);

    @RequestMapping(value = "v1/admins/getall", method = RequestMethod.GET)
    public String getAll();

    @RequestMapping(value = "v1/admins/search",method = RequestMethod.GET)
    public String search(@RequestParam(required = true, value = "key") String key);
}
