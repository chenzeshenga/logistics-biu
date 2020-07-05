package com.abc.chenzeshenga.logistics.model.ord;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.*;

/**
 * @author chenzesheng
 * @since 2020-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class OrderPackage extends BaseRowModel {

  private String uuid;

  @ExcelProperty(index = 0, value = "订单号")
  private String orderNo;

  @ExcelProperty(index = 1, value = "长（cm）")
  private double length;

  @ExcelProperty(index = 2, value = "宽（cm）")
  private double width;

  @ExcelProperty(index = 3, value = "高（cm）")
  private double height;

  @ExcelProperty(index = 4, value = "重（kg）")
  private double totalWeight;

  @ExcelProperty(index = 5, value = "承运人")
  private String carrierNo;

  @ExcelProperty(index = 6, value = "追踪单号")
  private String trackNo;
}
