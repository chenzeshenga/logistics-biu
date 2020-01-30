package com.abc.chenzeshenga.logistics.mapper.statistics;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-01-30 09:44
 */
@Mapper
public interface ProductInWarehouseMapper extends BaseMapper<ProductInWarehouseSummary> {

  List<ProductInWarehouseSummary> fetchProductInWarehouseWithManagerRole(
      Page page, String sku, String name, String owner);

  List<ProductInWarehouseSummary> fetchProductInWarehouseWithUserRole(
      Page page, String sku, String name, String username);
}
