package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.File;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper {
  int deleteByPrimaryKey(String uuid);

  int insert(File record);

  int insertWithName(File record);

  int insertSelective(File record);

  File selectByPrimaryKey(String uuid);

  File selectByPrimaryKeyWithName(String uuid);

  List<File> getFileListByUuid(@Param("uuids") List<String> uuids);

  int updateByPrimaryKeySelective(File record);

  int updateByPrimaryKeyWithBLOBs(File record);
}
