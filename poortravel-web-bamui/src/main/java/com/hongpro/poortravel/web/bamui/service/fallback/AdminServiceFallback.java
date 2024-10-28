package com.hongpro.poortravel.web.bamui.service.fallback;

import com.google.common.collect.Lists;
import com.hongpro.poortravel.common.constants.HttpStatusConstants;
import com.hongpro.poortravel.common.dto.BaseResult;
import com.hongpro.poortravel.common.utils.MapperUtils;
import com.hongpro.poortravel.web.bamui.service.AdminService;
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
    public String get(String userGuid) {
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

    @Override
    public String delete(String userJson) {
        return null;
    }

    @Override
    public String update(String userGuid, String userJson) {
        return null;
    }


    @Override
    public String getAll() {
        return null;
    }

    @Override
    public String search(String key) {
        return null;
    }

}
