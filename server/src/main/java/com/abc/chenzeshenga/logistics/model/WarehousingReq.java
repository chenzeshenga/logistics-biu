package com.abc.chenzeshenga.logistics.model;

import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import lombok.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WarehousingReq extends Pagination {

  private String warehousingNo;
  private String creator;
  private String channelCode;
  private String status;
  private String category;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date from;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date to;
}
