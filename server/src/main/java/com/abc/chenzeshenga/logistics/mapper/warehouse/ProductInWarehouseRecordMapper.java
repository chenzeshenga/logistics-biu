package com.abc.chenzeshenga.logistics.mapper.warehouse;

import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenzeshenga
 * @since 2020-06-01 23:11
 */
@Mapper
public interface ProductInWarehouseRecordMapper {

  int insert(ProductInWarehouse productInWarehouse);

  List<ProductInWarehouse> list(
      @Param("productInWarehouse") ProductInWarehouse productInWarehouse,
      @Param("sqlLimit") SqlLimit sqlLimit);

  Long count(@Param("productInWarehouse") ProductInWarehouse productInWarehouse);

  List<ProductInWarehouse> listCurrentDayProduct(
      @Param("start") Date start, @Param("end") Date end);
}
