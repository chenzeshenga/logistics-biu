package com.abc.chenzeshenga.logistics.model.ord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzesheng
 * @since 2020-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderPackage {

  private String uuid;
  private String orderNo;
  private double length;
  private double width;
  private double height;
  private String carrierNo;
  private String trackNo;
}
