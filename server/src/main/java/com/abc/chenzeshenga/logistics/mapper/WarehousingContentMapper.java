package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.model.WarehousingContentKey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface WarehousingContentMapper {
    int deleteByPrimaryKey(WarehousingContentKey key);

    int insert(WarehousingContent record);

    int insertList(List<WarehousingContent> warehousingContentList);

    int insertSelective(WarehousingContent record);

    WarehousingContent selectByPrimaryKey(WarehousingContentKey key);

    int updateByPrimaryKeySelective(WarehousingContent record);

    int updateByPrimaryKey(WarehousingContent record);

    List<WarehousingContent> listContent(String warehousingNo);

}
