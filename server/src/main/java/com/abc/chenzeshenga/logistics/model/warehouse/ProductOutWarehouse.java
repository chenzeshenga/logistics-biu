package com.abc.chenzeshenga.logistics.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author chenzeshenga
 * @since 2020-03-18 23:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductOutWarehouse {

  private String uuid;
  private String sku;
  private String num;
  private String owner;
  private String orderNo;
  private String trackNo;
  private Date outTime;
}
