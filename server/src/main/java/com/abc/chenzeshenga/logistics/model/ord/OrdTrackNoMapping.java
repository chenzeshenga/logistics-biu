package com.abc.chenzeshenga.logistics.model.ord;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chenzeshenga
 * @since 2020-02-04 19:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdTrackNoMapping {

  private String ordNo;
  private String carrierNo;
  private String trackNo;
}
