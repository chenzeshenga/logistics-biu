package com.abc.chenzeshenga.logistics.mapper.warehouse;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author chenzeshenga
 * @since 2020-06-01 23:11
 */
@Mapper
public interface ProductInWarehouseRecordMapper {

  int insert(ProductInWarehouse productInWarehouse);

  List<ProductInWarehouse> list(ProductInWarehouse productInWarehouse);
}
