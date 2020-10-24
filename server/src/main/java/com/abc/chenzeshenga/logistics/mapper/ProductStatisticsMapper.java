package com.abc.chenzeshenga.logistics.mapper;

import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatistics;
import com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatisticsReq;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Repository
public interface ProductStatisticsMapper extends BaseMapper<ProductStatistics> {

    int deleteByPrimaryKey(String sku);

    /**
     * truncate 整张统计表
     *
     * @return 更新数量
     */
    int deleteAll();

    @Override
    Integer insert(ProductStatistics record);

    @Deprecated
    int insertBatch(List<ProductStatistics> record);

    /**
     * 批量插入商品统计数据
     *
     * @param productInWarehouseStatisticsList 商品统计数据列表
     * @return 更新数量
     */
    int insertProductInWarehouseBatch(@Param("list") List<ProductInWarehouseStatistics> productInWarehouseStatisticsList);

    int insertSelective(ProductStatistics record);

    ProductStatistics selectByPrimaryKey(String sku);

    int updateByPrimaryKeySelective(ProductStatistics record);

    int updateByPrimaryKey(ProductStatistics record);

    List<ProductStatistics> selectAll(Pagination pagination);

    List<ProductStatistics> selectAllBySearch(
            Pagination pagination,
            @Param("sku") String sku,
            @Param("name") String name,
            @Param("owner") String owner);

    List<ProductStatistics> selectAllByUsername(String username);

    /**
     * 统计所有在库商品
     *
     * @return 数据列表
     */
    List<ProductInWarehouseStatistics> triggerCount();

    /**
     * 分页查询统计数据
     *
     * @param sqlLimit                        分页参数
     * @param productInWarehouseStatisticsReq 查询参数
     * @return 统计数据列表
     */
    List<ProductInWarehouseStatistics> select(@Param("sqlLimit") SqlLimit sqlLimit, @Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);

    /**
     * 计数
     *
     * @param productInWarehouseStatisticsReq 查询参数
     * @return 总数
     */
    Long count(@Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);

}
