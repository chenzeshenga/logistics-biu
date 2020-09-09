package com.abc.chenzeshenga.logistics.controller.product;

import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouse;
import com.abc.chenzeshenga.logistics.service.product.ProductInWarehouseRecordService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzesheng
 * @since 2020-05-27 22:20
 */
@RestController
@RequestMapping("/product/in")
public class ProductInWarehouseRecordController {

  private final ProductInWarehouseRecordService productInWarehouseRecordService;

  @Autowired
  public ProductInWarehouseRecordController(
      ProductInWarehouseRecordService productInWarehouseRecordService) {
    this.productInWarehouseRecordService = productInWarehouseRecordService;
  }

  @PostMapping("/warehouse/records")
  public Json listProductInWarehouseRecords(
      @RequestBody PageQueryEntity<ProductInWarehouse> productInWarehousePageQueryEntity) {
    PageData<ProductInWarehouse> productInWarehousePageData =
        productInWarehouseRecordService.list(productInWarehousePageQueryEntity);
    return Json.succ("query", "data", productInWarehousePageData);
  }
}
