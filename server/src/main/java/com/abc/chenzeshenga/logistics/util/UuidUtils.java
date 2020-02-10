package com.abc.chenzeshenga.logistics.util;

import java.util.UUID;

/**
 * @author chenzeshenga
 * @since 2020-02-10 22:10
 */
public class UuidUtils {

  public static String uuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }

}
