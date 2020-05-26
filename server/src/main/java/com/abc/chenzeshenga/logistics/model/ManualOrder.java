package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class ManualOrder extends BaseRowModel implements Serializable {

  private static final long serialVersionUID = 6816237345363318514L;
  /** pk */
  @NotEmpty(message = "订单号必填，且长度大于3个字符")
  @Size(min = 3, max = 255)
  @ExcelProperty(index = 0, value = "订单号")
  private String orderNo;

  @ExcelProperty(index = 1, value = "用户定义订单号")
  private String userCustomOrderNo;

  @ExcelProperty(index = 2, value = "当前状态")
  private String statusDesc;

  @ExcelProperty(index = 3, value = "创建者")
  private String creator;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @ExcelProperty(index = 4, value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
  private Date createOn;

  @ExcelProperty(index = 5, value = "承运人")
  private String carrierName;

  @ExcelProperty(index = 6, value = "追踪单号")
  private String trackNo;

  @ExcelProperty(index = 7, value = "订单类型")
  private String categoryName;

  @NotEmpty(message = "运送渠道必选")
  @ExcelProperty(index = 8, value = "运送渠道")
  private String channel;

  private String channelDesc;

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
  @NotEmpty(message = "收件人邮编必填")
  private String toZipCode;

  @ExcelProperty(index = 19, value = "收件人姓名")
  @NotEmpty(message = "收件人姓名必填")
  private String toName;

  @ExcelProperty(index = 20, value = "收件人联系方式")
  private String toContact;

  private String collect;

  @ExcelProperty(index = 21, value = "是否代收费用")
  private String collectDesc;
  /** 代收金额 */
  @ExcelProperty(index = 22, value = "代收费用总额(JPY)")
  private String collectNum;

  @ExcelProperty(index = 23, value = "东岳sku")
  private String sku1;

  @ExcelProperty(index = 24, value = "名称")
  private String name1;

  @ExcelProperty(index = 25, value = "数量")
  private String num1;

  @ExcelProperty(index = 26, value = "东岳sku")
  private String sku2;

  @ExcelProperty(index = 27, value = "名称")
  private String name2;

  @ExcelProperty(index = 28, value = "数量")
  private String num2;

  @ExcelProperty(index = 29, value = "东岳sku")
  private String sku3;

  @ExcelProperty(index = 30, value = "名称")
  private String name3;

  @ExcelProperty(index = 31, value = "数量")
  private String num3;

  private String status;
  private String carrierNo;
  private String chinaCarrier;
  private String updator;

  @NotEmpty(message = "订单类型必选")
  private String category;

  private String chinaNo;

  @NotEmpty(message = "订单内容必选")
  @JsonProperty("contentList")
  private List<ManualOrderContent> manualOrderContents;
  /** selected from address */
  private List<String> selectedAddress;

  @JsonProperty("selectedtoAddress")
  private List<String> selectedToAddress;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateOn;

  private String amount;
  private Map<String, String> address;
  private Map<String, String> toAddress;
  private String abnormalReason;
  private String ordFee;
  private boolean satisfied = true;

  private double length;
  private double width;
  private double height;
  private double sum;
  private double totalVolumeWithWeight;

  @JsonProperty("totalVolumeFrontEnd")
  private double totalVolume;

  private double totalWeight;
  private int pickup = 2;

  public ManualOrder(String orderNo, int pickup) {
    this.orderNo = orderNo;
    this.pickup = pickup;
  }
}
