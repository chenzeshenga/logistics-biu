package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ProductStatistic;
import org.springframework.stereotype.Repository;

@Repository public interface ProductStatisticMapper {
    int deleteByPrimaryKey(String sku);

    int insert(ProductStatistic record);

    int insertSelective(ProductStatistic record);

    ProductStatistic selectByPrimaryKey(String sku);

    int updateByPrimaryKeySelective(ProductStatistic record);

    int updateByPrimaryKey(ProductStatistic record);
}