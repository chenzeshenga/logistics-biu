package com.abc.chenzeshenga.logistics.model.v2.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * represent l_product_statistics
 *
 * @author chenzeshenga
 * @since 2020-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInWarehouseStatistics {

  private String uuid;
  private String dySku;
  private String productName;
  private String owner;
  private String shelfNo;
  private int totalNum;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date statisticalTime;

  private String date;

  private Double volume;
  private Double weight;
  private Double costOnVolume;
  private boolean adminUpdate;
  private String updateBy;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
  private Date updateTime;
}
