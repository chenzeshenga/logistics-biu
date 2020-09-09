package com.abc.chenzeshenga.logistics.service.product;

import com.abc.chenzeshenga.logistics.mapper.product.ProductOutWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:47
 */
@Service
@Slf4j
public class ProductOutWarehouseRecordService {

  @Resource private ProductOutWarehouseRecordMapper productOutWarehouseRecordMapper;

  public PageData<ProductOutWarehouse> list(
      PageQueryEntity<ProductOutWarehouse> productOutWarehousePageQueryEntity) {
    PageData<ProductOutWarehouse> result = new PageData<>();
    ProductOutWarehouse productOutWarehouse = productOutWarehousePageQueryEntity.getEntity();
    Pagination pagination = productOutWarehousePageQueryEntity.getPagination();
    List<ProductOutWarehouse> productOutWarehouseList =
        productOutWarehouseRecordMapper.list(
            productOutWarehouse, SqlUtils.generateSqlLimit(pagination));
    long total = productOutWarehouseRecordMapper.count(productOutWarehouse);
    result.setCurrent(productOutWarehousePageQueryEntity.getPagination().getCurrent());
    result.setTotal(total);
    result.setSize(productOutWarehousePageQueryEntity.getPagination().getSize());
    result.setData(productOutWarehouseList);
    return result;
  }
}
