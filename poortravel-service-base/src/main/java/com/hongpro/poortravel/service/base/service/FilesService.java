package com.hongpro.poortravel.service.base.service;

import com.hongpro.poortravel.common.domain.Files;
import com.hongpro.poortravel.common.service.BaseService;

import java.util.List;


public interface FilesService<T> extends BaseService<T> {
    public List<Files> search(String key);
}
