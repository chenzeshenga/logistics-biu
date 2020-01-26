package com.abc.chenzeshenga.logistics.util;

import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class SkuUtil {

  private static final String PATTERN = "[a-zA-Z]";

  private static final String EMPTY = "";

  private static final String CONNECT = "-";

  private SkuUtil() {}

  public static String generateDySku() {
    int a = 13;
    int b = 10;
    String uuid = UUID.randomUUID().toString().replaceAll(PATTERN, EMPTY).replace(CONNECT, EMPTY);
    if (uuid.length() >= a) {
      uuid = uuid.substring(0, 13);
    } else {
      uuid = StringUtils.leftPad(uuid, 13, "1");
    }
    uuid = uuid.substring(0, 12);
    int oddsum = 0;
    int evensum = 0;
    for (int i = uuid.length() - 1; i >= 0; i--) {
      if ((uuid.length() - i) % 2 == 0) {
        evensum += Character.digit(uuid.charAt(i), 10);
      } else {
        oddsum += Character.digit(uuid.charAt(i), 10);
      }
    }
    int check = 10 - ((evensum + 3 * oddsum) % 10);
    if (check >= b) {
      check = 0;
    }
    uuid = uuid + check;
    return uuid;
  }
}
