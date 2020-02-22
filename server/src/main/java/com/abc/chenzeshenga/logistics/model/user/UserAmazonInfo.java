package com.abc.chenzeshenga.logistics.model.user;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-02-22 21:56
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString public class UserAmazonInfo {

  @TableId(type = IdType.ID_WORKER_STR) private String userId;
  private String sellerId;
  private String marketplaceId;
  private String mwsAuthToken;

}
