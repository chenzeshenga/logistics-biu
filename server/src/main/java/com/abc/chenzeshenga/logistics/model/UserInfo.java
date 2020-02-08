package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 2019-12-28
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserInfo {

  private String userId;
  private String userAddress;
}
