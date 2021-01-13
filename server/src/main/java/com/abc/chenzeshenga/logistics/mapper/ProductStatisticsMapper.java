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

  /**
   * 根据日期和是否被管理人员修改删除历史表中的统计数据
   *
   * @param date 更新日期
   * @return 更新数量
   */
  int deleteCurrDayHistory(@Param("date") String date);

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
  int insertProductInWarehouseBatch(
      @Param("list") List<ProductInWarehouseStatistics> productInWarehouseStatisticsList);

  /**
   * 更新商品库存信息
   *
   * @param productInWarehouseStatistics 商品库存信息
   * @return 1
   */
  int updateHistoryProductStatistics(ProductInWarehouseStatistics productInWarehouseStatistics);

  /**
   * 根据东岳sku和当前日期查询历史商品统计信息
   *
   * @param dySku 东岳sku
   * @param currDate 当前日期
   * @return 历史商品统计信息
   */
  ProductInWarehouseStatistics selectHistoryBySkuAndDate(
      @Param("dySku") String dySku, @Param("currDate") String currDate);

  /**
   * 插入当日商品统计记录到当前表
   *
   * @param productInWarehouseStatistics 商品统计信息
   * @return 1
   */
  int insertProductInWarehouseSingle(ProductInWarehouseStatistics productInWarehouseStatistics);

  /**
   * 插入当日商品统计记录到历史表
   *
   * @param productInWarehouseStatistics 商品统计信息
   * @return 1
   */
  int insertProductInWarehouseHistorySingle(
      ProductInWarehouseStatistics productInWarehouseStatistics);

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
   * @param sqlLimit 分页参数
   * @param productInWarehouseStatisticsReq 查询参数
   * @return 统计数据列表
   */
  List<ProductInWarehouseStatistics> select(
      @Param("sqlLimit") SqlLimit sqlLimit,
      @Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);

  /**
   * 分页查询历史统计数据
   *
   * @param sqlLimit 分页参数
   * @param productInWarehouseStatisticsReq 查询参数
   * @return 统计数据列表
   */
  List<ProductInWarehouseStatistics> selectHistory(
      @Param("sqlLimit") SqlLimit sqlLimit,
      @Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);

  /**
   * 计数
   *
   * @param productInWarehouseStatisticsReq 查询参数
   * @return 总数
   */
  Long count(@Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);

  /**
   * 计数
   *
   * @param productInWarehouseStatisticsReq 查询参数
   * @return 总数
   */
  Long countHistory(@Param("req") ProductInWarehouseStatisticsReq productInWarehouseStatisticsReq);
}
