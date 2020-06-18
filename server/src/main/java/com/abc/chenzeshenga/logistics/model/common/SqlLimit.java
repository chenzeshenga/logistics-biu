package com.abc.chenzeshenga.logistics.model.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-03-8 23:25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SqlLimit {

  private long from;
  private long size;

}
