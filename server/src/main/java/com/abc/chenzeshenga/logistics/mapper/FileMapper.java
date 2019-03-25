package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.File;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Mapper public interface FileMapper {

    int deleteByPrimaryKey(String uuid);

    int insert(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String uuid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKeyWithBLOBs(File record);

}