package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import java.io.Serializable;
import lombok.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = true)
public class ManualOrderContent extends BaseRowModel implements Serializable {

  private static final long serialVersionUID = -1370202425183014350L;

  @ExcelProperty(index = 0, value = "订单号")
  private String ordno;

  @ExcelProperty(index = 1, value = "东岳sku")
  private String dySku;

  private String sku;

  @ExcelProperty(index = 2, value = "名称")
  private String name;

  @ExcelProperty(index = 3, value = "订单数量")
  private String num = "0";

  private String price;

  @ExcelProperty(index = 4, value = "发货数量")
  private String picked = "0";

  private boolean satisfied;
  private Double totalPrice;
  private String location;
  private String imgUrl;
  private String shelfNo;
  private String uuid;
}
