package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Template;
import org.springframework.stereotype.Repository;

@Repository public interface TemplateMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKeyWithBLOBs(Template record);

    int updateByPrimaryKey(Template record);
}