package com.hongpro.poortravel.service.base.service;

import com.hongpro.poortravel.common.domain.Tag;
import com.hongpro.poortravel.common.service.Impl.BaseServiceImpl;
import com.hongpro.poortravel.service.base.mapper.TagExtentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TagServiceImpl extends BaseServiceImpl<Tag,TagExtentMapper> implements TagService<Tag> {
    @Autowired
    private TagExtentMapper tagExtentMapper;

    @Override
    public List<Tag> search(String key) {
        Example example=new Example(Tag.class);
        String likekey = "%"+key+"%";
        if (key!=""&&key!=null) {
            example.createCriteria().andLike("label",likekey);
        }

        List<Tag> tags = tagExtentMapper.selectByExample(example);
        return tags;
    }
}
