package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Template;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface TemplateMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    List<Template> list();

}