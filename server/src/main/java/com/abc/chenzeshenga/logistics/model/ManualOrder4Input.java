package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ManualOrder4Input extends BaseRowModel implements Serializable {

  private static final long serialVersionUID = 4293084136427695346L;

  @ExcelProperty(index = 0, value = "用户自定义订单号")
  private String userCustomOrderNo;

  @ExcelProperty(index = 1, value = "订单类型")
  private String categoryName;

  @ExcelProperty(index = 2, value = "渠道类型")
  private String channelCode;

  @ExcelProperty(index = 3, value = "发件地址-道/府/县")
  private String fromAddressLine1;

  @ExcelProperty(index = 4, value = "发件地址-详细地址1")
  private String fromAddressLine2;

  @ExcelProperty(index = 5, value = "发件地址-详细地址2")
  private String fromAddressLine3;

  @ExcelProperty(index = 6, value = "发件地址邮编")
  private String fromZipCode;

  @ExcelProperty(index = 7, value = "发件人姓名")
  private String fromName;

  @ExcelProperty(index = 8, value = "发件人联系方式")
  private String fromContact;

  @ExcelProperty(index = 9, value = "收件地址-道/府/县")
  private String toAddressLine1;

  @ExcelProperty(index = 10, value = "收件地址-详细地址1")
  private String toAddressLine2;

  @ExcelProperty(index = 11, value = "收件地址-详细地址2")
  private String toAddressLine3;

  @ExcelProperty(index = 12, value = "收件地址邮编")
  private String toZipCode;

  @ExcelProperty(index = 13, value = "收件人姓名")
  private String toName;

  @ExcelProperty(index = 14, value = "收件人联系方式")
  private String toContact;

  @ExcelProperty(index = 15, value = "是否代收费用")
  private String collectDesc;

  @ExcelProperty(index = 16, value = "代收费用总额(JPY)")
  private String collectNum;

  @ExcelProperty(index = 17, value = "sku")
  private String sku1;

  @ExcelProperty(index = 18, value = "名称")
  private String name1;

  @ExcelProperty(index = 19, value = "数量")
  private String num1;

  @ExcelProperty(index = 20, value = "sku")
  private String sku2;

  @ExcelProperty(index = 21, value = "名称")
  private String name2;

  @ExcelProperty(index = 22, value = "数量")
  private String num2;

  @ExcelProperty(index = 23, value = "sku")
  private String sku3;

  @ExcelProperty(index = 24, value = "名称")
  private String name3;

  @ExcelProperty(index = 25, value = "数量")
  private String num3;
}
