package com.abc.chenzeshenga.logistics.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 2019.07.20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReturnContent implements Serializable {

  private static final long serialVersionUID = 999405990725459271L;

  @TableId(type = IdType.ID_WORKER_STR)
  private String uuid;

  private String returnNo;

  private String sku;

  private String name;

  private String num;

  private String dealWith;
}
