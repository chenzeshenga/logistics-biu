package com.abc.chenzeshenga.logistics.model;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
public class TrackNo {

  private int id;

  @NotEmpty(message = "承运人必选")
  private String carrier;

  private String carrierDesc;

  @NotEmpty(message = "关联渠道必填")
  private String channelCode;

  private String channelName;

  @NotEmpty(message = "最小追踪单号必填")
  private String min;

  @NotEmpty(message = "最大追踪单号必填")
  private String max;
}
