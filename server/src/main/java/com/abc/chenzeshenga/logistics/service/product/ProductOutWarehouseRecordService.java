package com.abc.chenzeshenga.logistics.service.product;

import com.abc.chenzeshenga.logistics.mapper.product.ProductOutWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductOutWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.common.Page;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:47
 */
@Service
@Slf4j
public class ProductOutWarehouseRecordService {

  @Resource private ProductOutWarehouseRecordMapper productOutWarehouseRecordMapper;

  public Page<ProductOutWarehouse> list(
      PageQueryEntity<ProductOutWarehouse> productOutWarehousePageQueryEntity) {
    ProductOutWarehouse productOutWarehouse = productOutWarehousePageQueryEntity.getEntity();
    Pagination pagination = productOutWarehousePageQueryEntity.getPagination();
    productOutWarehouseRecordMapper.list(
        productOutWarehouse, SqlUtils.generateSqlLimit(pagination));
    return new Page<>();
  }
}
