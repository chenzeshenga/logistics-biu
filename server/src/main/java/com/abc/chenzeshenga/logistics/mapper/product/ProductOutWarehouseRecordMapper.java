package com.abc.chenzeshenga.logistics.mapper.product;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:52
 */
@Mapper
public interface ProductOutWarehouseRecordMapper {

  List<ProductOutWarehouse> list(
      @Param("productOutWarehouseRecord") ProductOutWarehouse productOutWarehouse,
      @Param("sqlLimit") SqlLimit sqlLimit);

  long count(@Param("productOutWarehouseRecord") ProductOutWarehouse productOutWarehouse);
}
