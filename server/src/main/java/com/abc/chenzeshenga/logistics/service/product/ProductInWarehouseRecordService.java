package com.abc.chenzeshenga.logistics.service.product;

import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductInWarehouseRecordMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chenzesheng
 * @since 2020-06-02 22:47
 */
@Service
@Slf4j
public class ProductInWarehouseRecordService {

  @Resource private ProductInWarehouseRecordMapper productInWarehouseRecordMapper;

  public PageData<ProductInWarehouse> list(
      PageQueryEntity<ProductInWarehouse> productInWarehousePageQueryEntity) {
    PageData<ProductInWarehouse> result = new PageData<>();
    ProductInWarehouse productInWarehouse = productInWarehousePageQueryEntity.getEntity();
    Pagination pagination = productInWarehousePageQueryEntity.getPagination();
    List<ProductInWarehouse> productInWarehouseList =
        productInWarehouseRecordMapper.list(
            productInWarehouse, SqlUtils.generateSqlLimit(pagination));
    long total = productInWarehouseRecordMapper.count(productInWarehouse);
    result.setCurrent(productInWarehousePageQueryEntity.getPagination().getCurrent());
    result.setTotal(total);
    result.setSize(productInWarehousePageQueryEntity.getPagination().getSize());
    result.setData(productInWarehouseList);
    return result;
  }

  public List<ProductInWarehouse> listCurrentDayProduct() {
    Date curr = new Date();
    Date start = DateUtil.genStartDate(curr);
    Date end = DateUtil.genEndDate(curr);
    if (start != null && end != null) {
      return productInWarehouseRecordMapper.listCurrentDayProduct(start, end);
    }
    return Collections.emptyList();
  }
}
