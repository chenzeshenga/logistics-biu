package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.Data;
import lombok.ToString;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190819
 */
@Data
@ToString
public class JpDetailAddress implements Serializable {

  private static final long serialVersionUID = 4664906408754186144L;

  private String kenId;

  private String kenName;

  private String cityId;

  private String cityName;

  private String townId;

  private String townName;

  private String zip;
}
