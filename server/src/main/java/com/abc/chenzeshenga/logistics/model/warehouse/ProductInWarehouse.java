package com.abc.chenzeshenga.logistics.model.warehouse;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 商品出库记录实体类
 *
 * @author chenzeshenga
 * @since 2020-03-18 23:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductInWarehouse {

  private String uuid;
  private String num;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date inTime;

  // follow properties support search from front end

  private String dySku = "";
  private String sku;

  private String owner;
  private String warehousingNo;
  private String shelfNo;
  /** property for search */
  private Date startDate;
  /** property for search */
  private Date endDate;
}
