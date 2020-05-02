package com.abc.chenzeshenga.logistics.controller.product;

import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.service.product.ProductOutWarehouseRecordService;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzesheng
 * @since 2020-05-02 08:40
 */
@RestController("/product/out")
public class ProductOutWarehouseRecordController {

  private ProductOutWarehouseRecordService productOutWarehouseRecordService;

  @Autowired
  public ProductOutWarehouseRecordController(
      ProductOutWarehouseRecordService productOutWarehouseRecordService) {
    this.productOutWarehouseRecordService = productOutWarehouseRecordService;
  }

  @PostMapping("/warehouse/records")
  public Json listProductOutWarehouseRecords(@RequestBody ProductOutWarehouse productOutWarehouse) {
    productOutWarehouseRecordService.list(productOutWarehouse);
    return Json.succ();
  }
}
