package com.abc.chenzeshenga.logistics.model.shelf;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author chenzesheng
 * @since 2020-06-22 06:42
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UpShelfProductInFile extends BaseRowModel {

  @ExcelProperty(index = 0, value = "东岳sku")
  private String dySku;

  @ExcelProperty(index = 1, value = "数量")
  private String num;

  @ExcelProperty(index = 2, value = "货架号")
  private String shelfNo;

  @ExcelProperty(index = 3, value = "入库时间")
  private String inTime;

  @ExcelProperty(index = 4, value = "入库单号")
  private String warehousingNo;
}
