package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.model.WarehousingContentKey;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehousingContentMapper {
  int deleteByPrimaryKey(WarehousingContentKey key);

  int deleteByWarehousingNo(String warehousingNo);

  int insert(WarehousingContent record);

  int insertList(List<WarehousingContent> warehousingContentList);

  int insertSelective(WarehousingContent record);

  WarehousingContent selectByPrimaryKey(WarehousingContentKey key);

  int updateByPrimaryKeySelective(WarehousingContent record);

  int updateByPrimaryKey(WarehousingContent record);

  List<WarehousingContent> listContent(String warehousingNo);

  int deleteContent(String warehousingNo);

  int updateContentHeadCheck(WarehousingContent warehousingContent);
}
