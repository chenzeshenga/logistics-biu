package com.abc.chenzeshenga.logistics.model.warehouse;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
public class ProductOutWarehouse {

  private String uuid;
  private String dySku = "";
  private String sku;
  private String num;
  private String owner;
  private String orderNo;
  private String trackNo;
  private Date outTime;
  private int hoursInWarehouse;

  /** property for search */
  private Date startDate;
  /** property for search */
  private Date endDate;
}
