package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Product extends BaseRowModel implements Serializable, Cloneable {

  @ExcelProperty(index = 0, value = "sku")
  @NotEmpty(message = "商品sku必填")
  private String sku;

  @ExcelProperty(index = 1, value = "dySku")
  private String dySku;

  @ExcelProperty(index = 2, value = "商品名称")
  @NotEmpty(message = "商品名称必填")
  private String productName;

  @NotEmpty(message = "商品类型必选")
  private String category;

  @ExcelProperty(index = 3, value = "商品类型")
  private String categoryName;

  @ExcelProperty(index = 4, value = "颜色")
  private String color;

  @ExcelProperty(index = 5, value = "尺寸")
  private String size;

  @ExcelProperty(index = 6, value = "长(cm)")
  private String length;

  @ExcelProperty(index = 7, value = "宽(cm)")
  private String width;

  @ExcelProperty(index = 8, value = "高(cm)")
  private String height;

  @ExcelProperty(index = 9, value = "重量(kg)")
  private String weight;

  @ExcelProperty(index = 10, value = "价格(JPY)")
  @NotEmpty(message = "商品价格必填")
  private String price;

  private String createdBy;
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date createOn;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateOn;

  private int alertNum;
  private String location;
  private String imgPath;
  private String avaNum;
  private String img1;
  private String img2;
  private String img3;
  private String status;
  private String statusDesc;
  private List<ProductOnShelf> productOnShelfList;
}
