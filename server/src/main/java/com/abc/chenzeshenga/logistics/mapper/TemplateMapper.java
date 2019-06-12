package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.CommonObj;
import com.abc.chenzeshenga.logistics.model.Template;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface TemplateMapper extends BaseMapper<Template> {
    int deleteByPrimaryKey(String uuid);

    Integer insert(Template record);

    int insertSelective(Template record);

    Template selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(Template record);

    int updateByPrimaryKey(Template record);

    List<Template> list(Pagination pagination, CommonObj reg);

}
