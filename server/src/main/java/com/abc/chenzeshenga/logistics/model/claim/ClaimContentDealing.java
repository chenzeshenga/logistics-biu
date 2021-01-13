package com.abc.chenzeshenga.logistics.model.claim;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @since 2020-09-13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClaimContentDealing implements Serializable {

  private String uuid;
  private String returnNo;
  private String sku;
  private String name;
  private String num;
  private String dealWith;
  private String shelfNo;
  private String comment;
}
