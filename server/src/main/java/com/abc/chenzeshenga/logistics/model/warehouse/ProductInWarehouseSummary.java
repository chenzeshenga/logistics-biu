package com.abc.chenzeshenga.logistics.model.warehouse;

import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotations.TableName;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzeshenga
 * @since 2020-01-29 23:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("l_up_shelf_product")
public class ProductInWarehouseSummary extends BaseRowModel {
  @ExcelProperty(index = 0, value = "东岳sku")
  private String dySku;

  @ExcelProperty(index = 1, value = "名称")
  private String name;

  @ExcelProperty(index = 2, value = "在库合计")
  private String num;

  @ExcelProperty(index = 3, value = "属主")
  private String owner;

  private String shelfNo;
  private String warehousingNo;
  private String totalWarehousingTime;
  private List<UpShelfProduct> children = new ArrayList<>();
  private List<ProductOutWarehouse> productOutWarehouseList = new ArrayList<>();
}
