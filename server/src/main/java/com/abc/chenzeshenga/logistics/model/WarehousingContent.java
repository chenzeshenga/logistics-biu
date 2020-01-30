package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * l_warehousing_content
 *
 * @author chenzesheng
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WarehousingContent extends WarehousingContentKey implements Serializable {

  private static final long serialVersionUID = 6943231354146357407L;
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
