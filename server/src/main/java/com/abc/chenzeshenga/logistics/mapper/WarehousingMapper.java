package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.Warehousing;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository public interface WarehousingMapper {
    int deleteByPrimaryKey(String warehousingNo);

    int insert(Warehousing record);

    int insertSelective(Warehousing record);

    Warehousing selectByPrimaryKey(String warehousingNo);

    int updateByPrimaryKeySelective(Warehousing record);

    int updateByPrimaryKey(Warehousing record);
}