package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
public class ManualOrderContent implements Serializable {

  private static final long serialVersionUID = -1370202425183014350L;

  private String sku;

  private String name;

  private String num = "0";

  private String price;

  private String ordno;

  private String picked = "0";

  private boolean satisfied;

  private Double totalPrice;

  private String location;

  private String imgUrl;

  private String shelfNo;

  private String uuid;
}
