package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
class ProductOnShelf implements Serializable {

  private String sku;
  private String dySku;
  private String name;
  private String location;
  private String num;
}
