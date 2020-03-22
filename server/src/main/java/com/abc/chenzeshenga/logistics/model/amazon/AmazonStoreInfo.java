package com.abc.chenzeshenga.logistics.model.amazon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-03-22 22:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AmazonStoreInfo {

  private String uuid;
  private String userId;
  private String sellerId;
  private String mwsAuthToken;
  private String marketplaceId;
  private boolean enable;
}
