package com.abc.chenzeshenga.logistics.mapper.product;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:52
 */
@Mapper
public interface ProductOutWarehouseRecordMapper {

  List<ProductOutWarehouse> list(
      @Param("productOutWarehouseRecord") ProductOutWarehouse productOutWarehouse,
      @Param("sqlLimit") SqlLimit sqlLimit);

  int count(ProductOutWarehouse productOutWarehouse);
}
