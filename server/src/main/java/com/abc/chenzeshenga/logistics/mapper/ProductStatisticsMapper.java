package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Repository public interface ProductStatisticsMapper {

    int deleteByPrimaryKey(String sku);

    int deleteAll();

    int insert(ProductStatistics record);

    int insertBatch(List<ProductStatistics> record);

    int insertSelective(ProductStatistics record);

    ProductStatistics selectByPrimaryKey(String sku);

    int updateByPrimaryKeySelective(ProductStatistics record);

    int updateByPrimaryKey(ProductStatistics record);

    List<ProductStatistics> selectAll();

    List<ProductStatistics> selectAllByUsername(String username);

}