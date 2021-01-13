package com.abc.chenzeshenga.logistics.model.v2.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzeshenga
 * @since 2020-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInWarehouseStatisticsReq {

  private String dySku;
  private String owner;
  private String shelfNo;
}
