package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * l_product_statistic
 *
 * @author chenz
 * @deprecated replaced by
 *     com.abc.chenzeshenga.logistics.model.v2.statistics.ProductInWarehouseStatistics
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Deprecated
public class ProductStatistics implements Serializable {

  private static final long serialVersionUID = -7084156648221414824L;

  private String sku;

  private String dySku;

  private String owner;

  private String totalNum;

  private String onWayNum;

  private String uncheckNum;

  private String pendingPickupNum;

  private String pendingDeliverNum;

  private String defectNum;

  private String uuid;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date statisticalTime = new Date();
}
