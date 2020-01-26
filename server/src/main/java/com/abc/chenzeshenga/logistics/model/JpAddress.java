package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import lombok.Data;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file JpAddress
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 22:27
 * @updateOn 2019-3-6 22:27
 */
@Data
public class JpAddress implements Serializable {

  private static final long serialVersionUID = -8740664592535035911L;

  private String id;

  private String name;
}
