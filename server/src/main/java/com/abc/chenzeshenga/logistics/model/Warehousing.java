package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.poi.ss.usermodel.CellStyle;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/** @author chenzeshenga */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@TableName("l_warehousing")
public class Warehousing extends BaseRowModel implements Serializable {

  private static final long serialVersionUID = -4866257602133972659L;

  @TableField(exist = false)
  private Map<Integer, CellStyle> cellStyleMap;
  /** 用户发货地址 */
  @TableField(exist = false)
  private String fromAddress;
  @ExcelProperty(index = 0, value = "目的仓库")
  private String target;
  @ExcelProperty(index = 1, value = "入库单号")
  @NotEmpty(message = "入库单号必填")
  @TableId(type = IdType.ID_WORKER_STR)
  private String warehousingNo;
  /** 头程方式 */
  @ExcelProperty(index = 2, value = "头程方式")
  @NotEmpty(message = "头程方式必填")
  private String method;
  /** 头程承运人 */
  @ExcelProperty(index = 3, value = "头程承运人")
  private String carrier;
  /** 承运人追踪号 */
  @ExcelProperty(index = 4, value = "承运人追踪号")
  private String trackNo;
  /** 日本头程承运人 */
  @ExcelProperty(index = 5, value = "头程承运人")
  private String carrierInJapan;
  /** 日本承运人追踪号 */
  @ExcelProperty(index = 6, value = "承运人追踪号")
  private String trackNoInJapan;
  /** 头程渠道 */
  @ExcelProperty(index = 7, value = "头程渠道")
  @TableField(exist = false)
  private String channel;
  /** 渠道描述 */
  @ExcelProperty(index = 8, value = "渠道描述")
  @TableField(exist = false)
  private String channelDesc;
  /** 运输方式 */
  @ExcelProperty(index = 9, value = "运输方式")
  @TableField(exist = false)
  private String deliverMethod;
  /** 报关类型 */
  @ExcelProperty(index = 10, value = "报关类型")
  @TableField(exist = false)
  private String clearanceType;
  /** 关税类型 */
  @ExcelProperty(index = 11, value = "关税类型")
  @TableField(exist = false)
  private String taxType;
  /** 保险服务 Y/N */
  @ExcelProperty(index = 12, value = "保险服务")
  @TableField(exist = false)
  private String insurance;
  /** 保险金额 */
  @TableField(exist = false)
  @ExcelProperty(index = 13, value = "保险金额")
  private String insuranceNum;
  /** 预计到港时间 */
  @TableField(exist = false)
  @ExcelProperty(index = 14, value = "预计到港时间")
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date estimatedDate;

  @TableField(exist = false)
  @ExcelProperty(index = 15, value = "状态")
  private String statusDesc;

  @TableField(exist = false)
  @ExcelProperty(index = 16, value = "sku")
  private String sku1;

  @ExcelProperty(index = 17, value = "名称")
  @TableField(exist = false)
  private String name1;

  @ExcelProperty(index = 18, value = "数量")
  @TableField(exist = false)
  private String num1;

  @ExcelProperty(index = 19, value = "包装方式")
  @TableField(exist = false)
  private String wrapType1;

  @ExcelProperty(index = 20, value = "sku")
  @TableField(exist = false)
  private String sku2;

  @ExcelProperty(index = 21, value = "名称")
  @TableField(exist = false)
  private String name2;

  @ExcelProperty(index = 22, value = "数量")
  @TableField(exist = false)
  private String num2;

  @ExcelProperty(index = 23, value = "包装方式")
  @TableField(exist = false)
  private String wrapType2;

  @ExcelProperty(index = 23, value = "sku")
  @TableField(exist = false)
  private String sku3;

  @ExcelProperty(index = 24, value = "名称")
  @TableField(exist = false)
  private String name3;

  @ExcelProperty(index = 25, value = "数量")
  @TableField(exist = false)
  private String num3;

  @ExcelProperty(index = 26, value = "包装方式")
  @TableField(exist = false)
  private String wrapType3;

  @ExcelProperty(index = 27, value = "创建时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField(exist = false)
  private Date createOn;

  @ExcelProperty(index = 28, value = "更新时间")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  @TableField(exist = false)
  private Date updateOn;

  @ExcelProperty(index = 29, value = "创建者")
  private String creator;

  @ExcelProperty(index = 30, value = "更新者")
  @TableField(exist = false)
  private String updator;

  @TableField(exist = false)
  private String status;

  @NotEmpty(message = "入库商品必填")
  @TableField(exist = false)
  private List<WarehousingContent> warehousingContentList;

  @TableField(exist = false)
  private String userWarehousingFileUuid;
  @TableField(exist = false)
  private String systemFileUuid;
}
