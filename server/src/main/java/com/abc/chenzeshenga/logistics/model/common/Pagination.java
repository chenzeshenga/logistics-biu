package com.abc.chenzeshenga.logistics.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-03-08 22:38
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Pagination {

  private long total;
  private long curr;
  private int size;
}
