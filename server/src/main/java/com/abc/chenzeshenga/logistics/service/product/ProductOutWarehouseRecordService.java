package com.abc.chenzeshenga.logistics.service.product;

import com.abc.chenzeshenga.logistics.model.common.Page;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:47
 */
@Service
@Slf4j
public class ProductOutWarehouseRecordService {



  public Page<ProductOutWarehouse> list(ProductOutWarehouse productOutWarehouse) {

    return new Page<>();
  }
}
