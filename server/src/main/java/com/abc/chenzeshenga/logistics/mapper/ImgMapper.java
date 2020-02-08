package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Img;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Mapper
public interface ImgMapper {

  int deleteByPrimaryKey(String uuid);

  int insert(Img record);

  int insertSelective(Img record);

  Img selectByPrimaryKey(String uuid);

  int updateByPrimaryKeySelective(Img record);

  int updateByPrimaryKeyWithBLOBs(Img record);
}
