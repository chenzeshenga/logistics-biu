package com.abc.chenzeshenga.logistics.mapper.statistics;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-01-30 09:44
 */
@Mapper
public interface ProductInWarehouseMapper extends BaseMapper<ProductInWarehouseSummary> {

  List<ProductInWarehouseSummary> fetchProductInWarehouseWithManagerRole(
      String dySku, String name, String owner, long from, long size);

  List<ProductInWarehouseSummary> fetchAllProductInWarehouseWithManagerRole(
      String dySku, String name, String owner);

  List<ProductInWarehouseSummary> fetchProductInWarehouseWithUserRole(
      String dySku, String name, String username, long from, long size);

  List<ProductInWarehouseSummary> fetchAllProductInWarehouseWithUserRole(
      String dySku, String name, String username);

  long countProductInWarehouseWithUserRole(String dySku, String name, String owner);

  long countProductInWarehouseWithManagerRole(String dySku, String name, String owner);
}
