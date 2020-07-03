package com.abc.chenzeshenga.logistics.model.ord;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

/**
 * @author chenzeshenga
 * @since 2020-07-03 0:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ManualOrderV2 extends BaseRowModel {

  @ExcelProperty(index = 0, value = "订单号")
  private String orderNo;

  @ExcelProperty(index = 1, value = "用户定义订单号")
  private String userCustomOrderNo;

  private String status;

  @ExcelProperty(index = 2, value = "当前状态")
  private String statusDesc;

  @ExcelProperty(index = 3, value = "创建者")
  private String creator;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ExcelProperty(index = 4, value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
  private Date createOn;

  private String carrierNo;

  @ExcelProperty(index = 5, value = "承运人")
  private String carrierName;

  @ExcelProperty(index = 6, value = "追踪单号")
  private String trackNo;

  private String category;

  @ExcelProperty(index = 7, value = "订单类型")
  private String categoryName;

  @ExcelProperty(index = 8, value = "运送渠道")
  private String channel;

  @ExcelProperty(index = 9, value = "发件地址-道/府/县-城市-乡镇")
  private String fromAddressLine1;

  @ExcelProperty(index = 10, value = "发件地址1")
  private String fromAddressLine2;

  @ExcelProperty(index = 11, value = "发件地址2")
  private String fromAddressLine3;

  @ExcelProperty(index = 12, value = "发件地址邮编")
  private String fromZipCode;

  @ExcelProperty(index = 13, value = "发件人姓名")
  private String fromName;

  @ExcelProperty(index = 14, value = "发件人联系方式")
  private String fromContact;

  @ExcelProperty(index = 15, value = "收件地址-道/府/县-城市-乡镇")
  private String toAddressLine1;

  @ExcelProperty(index = 16, value = "收件地址1")
  private String toAddressLine2;

  @ExcelProperty(index = 17, value = "收件地址2")
  private String toAddressLine3;

  @ExcelProperty(index = 18, value = "收件地址邮编")
  private String toZipCode;

  @ExcelProperty(index = 19, value = "收件人姓名")
  private String toName;

  @ExcelProperty(index = 20, value = "收件人联系方式")
  private String toContact;

  private String onBehalf;

  @ExcelProperty(index = 21, value = "是否代收费用")
  private String collectDesc;
  /** 代收金额 */
  @ExcelProperty(index = 22, value = "代收费用总额(JPY)")
  private String collectNum;
}
