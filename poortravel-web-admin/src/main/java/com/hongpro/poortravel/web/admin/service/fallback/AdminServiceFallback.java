package com.hongpro.poortravel.web.admin.service.fallback;

import com.google.common.collect.Lists;
import com.hongpro.poortravel.common.constants.HttpStatusConstants;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.web.admin.service.AdminService;
import org.springframework.stereotype.Component;

@Component
public class AdminServiceFallback implements AdminService {
    @Override
    public String login(String username, String password) {
        BaseResult baseResult = BaseResult.notOk(Lists.newArrayList(new BaseResult.Error(String.valueOf(HttpStatusConstants.BAD_GATEWAY.getStatus()), HttpStatusConstants.BAD_GATEWAY.getContent())));
        try {
            return MapperUtils.obj2json(baseResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String get(int userGuid) {
        return null;
    }

    @Override
    public String page(int pageNum, int pageSize, String postsJson) {
        return null;
    }

    @Override
    public String save(String usersJson) {
        return null;
    }

}
