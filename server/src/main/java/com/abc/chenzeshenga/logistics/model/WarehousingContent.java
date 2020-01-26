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

  private String totalNum;
  private String wrapType;
  private String name;
  private String price;
  private String weight;
  private boolean received;
  private String actual;
  private String uuid;
}
