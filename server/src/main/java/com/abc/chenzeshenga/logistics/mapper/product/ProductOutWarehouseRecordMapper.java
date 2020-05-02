package com.abc.chenzeshenga.logistics.mapper.product;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:52
 */
@Mapper
public interface ProductOutWarehouseRecordMapper {

  List<ProductOutWarehouse> list(ProductOutWarehouse productOutWarehouse, SqlLimit sqlLimit);

  int count(ProductOutWarehouse productOutWarehouse);
}
