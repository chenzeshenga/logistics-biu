package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Repository public interface ProductStatisticsMapper extends BaseMapper<ProductStatistics> {

    int deleteByPrimaryKey(String sku);

    int deleteAll();

    @Override Integer insert(ProductStatistics record);

    int insertBatch(List<ProductStatistics> record);

    int insertSelective(ProductStatistics record);

    ProductStatistics selectByPrimaryKey(String sku);

    int updateByPrimaryKeySelective(ProductStatistics record);

    int updateByPrimaryKey(ProductStatistics record);

    List<ProductStatistics> selectAll(Pagination pagination);

    List<ProductStatistics> selectAllByUsername(String username);

}
