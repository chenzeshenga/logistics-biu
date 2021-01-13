package com.abc.chenzeshenga.logistics.temp.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import java.io.Serializable;
import lombok.*;

/**
 * @author chenzeshenga
 * @since 2020-05-07 22:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TmpOrder extends BaseRowModel implements Serializable {

  @ExcelProperty(index = 0, value = "用户自定义订单号")
  private String userCustomOrderNo;

  @ExcelProperty(index = 1, value = "收件地址邮编")
  private String toZipCode;
}
