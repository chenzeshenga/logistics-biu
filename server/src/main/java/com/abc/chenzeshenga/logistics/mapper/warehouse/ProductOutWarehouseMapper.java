package com.abc.chenzeshenga.logistics.mapper.warehouse;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-03-18 23:11
 */
@Mapper
public interface ProductOutWarehouseMapper {

  int insert(ProductOutWarehouse productOutWarehouse);

  List<ProductOutWarehouse> list(String owner, String sku);
}
