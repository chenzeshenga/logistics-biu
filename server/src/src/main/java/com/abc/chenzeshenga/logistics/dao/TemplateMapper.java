package com.abc.chenzeshenga.logistics.dao;

import com.abc.chenzeshenga.logistics.entity.Template;
import com.abc.chenzeshenga.logistics.entity.TemplateExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TemplateMapper {
    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    long countByExample(TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int deleteByExample(TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int deleteByPrimaryKey(String uuid);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int insert(Template record);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int insertSelective(Template record);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    List<Template> selectByExampleWithBLOBs(TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    List<Template> selectByExample(TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    Template selectByPrimaryKey(String uuid);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByExampleSelective(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByExampleWithBLOBs(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByExample(@Param("record") Template record, @Param("example") TemplateExample example);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByPrimaryKeySelective(Template record);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByPrimaryKeyWithBLOBs(Template record);

    /**
     *
     * @mbg.generated Wed Apr 03 00:39:48 GMT+08:00 2019
     */
    int updateByPrimaryKey(Template record);
}