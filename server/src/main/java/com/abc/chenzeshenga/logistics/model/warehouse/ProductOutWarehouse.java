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
  private int hoursInWarehouse;
  private String num;
  private Date outTime;

  // follow properties support search from front end

  private String dySku = "";
  private String sku;
  /** fk from product table and linked by dySku */
  private String name;

  private String owner;
  private String orderNo;
  private String trackNo;
  /** property for search */
  private Date startDate;
  /** property for search */
  private Date endDate;
}
