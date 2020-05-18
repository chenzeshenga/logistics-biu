package com.abc.chenzeshenga.logistics.model.warehouse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-05-18 23:06
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingContent {
  private String warehousingNo;
  private String sku;
  private String boxSeq;
  private String totalNum;
  private String wrapType;
  private String name;
  private String price;
  private boolean received;
  private String actual;
  private String uuid;
  private String length;
  private String width;
  private String height;
  private String weight;
}
