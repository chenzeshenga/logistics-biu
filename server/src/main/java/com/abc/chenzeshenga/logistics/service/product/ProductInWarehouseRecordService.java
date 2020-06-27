package com.abc.chenzeshenga.logistics.service.product;

import com.abc.chenzeshenga.logistics.mapper.product.ProductOutWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductInWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.model.common.Page;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzesheng
 * @since 2020-06-02 22:47
 */
@Service
@Slf4j
public class ProductInWarehouseRecordService {

  @Resource private ProductInWarehouseRecordMapper productInWarehouseRecordMapper;

  public Page<ProductInWarehouse> list(
      PageQueryEntity<ProductInWarehouse> productInWarehousePageQueryEntity) {
    Page<ProductInWarehouse> result = new Page<>();
    ProductInWarehouse productInWarehouse = productInWarehousePageQueryEntity.getEntity();
    Pagination pagination = productInWarehousePageQueryEntity.getPagination();
    List<ProductInWarehouse> productInWarehouseList =
        productInWarehouseRecordMapper.list(
            productInWarehouse, SqlUtils.generateSqlLimitV2(pagination));
    long total = productInWarehouseRecordMapper.count(productInWarehouse);
    result.setCurrent(productInWarehousePageQueryEntity.getPagination().getCurrent());
    result.setTotal(total);
    result.setSize(productInWarehousePageQueryEntity.getPagination().getSize());
    result.setData(productInWarehouseList);
    return result;
  }
}