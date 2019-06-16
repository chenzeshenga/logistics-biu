package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.File;
import org.springframework.stereotype.Repository;

@Repository public interface FileMapper {
    int deleteByPrimaryKey(String uuid);

    int insert(File record);

    int insertWithName(File record);

    int insertSelective(File record);

    File selectByPrimaryKey(String uuid);

    File selectByPrimaryKeyWithName(String uuid);

    int updateByPrimaryKeySelective(File record);

    int updateByPrimaryKeyWithBLOBs(File record);
}
