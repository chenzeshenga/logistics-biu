package com.abc.chenzeshenga.logistics.model.dev;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-02-22 21:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AmazonDevInfo {

  @TableId(type = IdType.ID_WORKER_STR)
  private String awsAccessKeyId;

  private String secretKey;
}
