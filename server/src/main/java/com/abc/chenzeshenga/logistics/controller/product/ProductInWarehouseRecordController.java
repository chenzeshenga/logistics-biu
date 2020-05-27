package com.abc.chenzeshenga.logistics.controller.product;

import com.abc.chenzeshenga.logistics.model.common.Page;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.service.product.ProductOutWarehouseRecordService;
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

  //  private ProductOutWarehouseRecordService productOutWarehouseRecordService;
  //
  //  @Autowired
  //  public ProductInWarehouseRecordController(
  //      ProductOutWarehouseRecordService productOutWarehouseRecordService) {
  //    this.productOutWarehouseRecordService = productOutWarehouseRecordService;
  //  }
  //
  //  @PostMapping("/warehouse/records")
  //  public Json listProductOutWarehouseRecords(
  //      @RequestBody PageQueryEntity<ProductOutWarehouse> productOutWarehousePageQueryEntity) {
  //    Page<ProductOutWarehouse> productOutWarehousePage=
  // productOutWarehouseRecordService.list(productOutWarehousePageQueryEntity);
  //    return Json.succ("query", "data", productOutWarehousePage);
  //  }
}
