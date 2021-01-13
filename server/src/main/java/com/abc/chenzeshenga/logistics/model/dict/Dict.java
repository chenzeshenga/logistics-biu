package com.abc.chenzeshenga.logistics.model.dict;

import java.util.Date;
import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-12-01 21:43
 */
@Data
public class Dict {

  private Integer id;
  private String key;
  private String value;
  private Date ctime;
}
