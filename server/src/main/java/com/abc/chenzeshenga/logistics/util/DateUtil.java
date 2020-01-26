package com.abc.chenzeshenga.logistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class DateUtil {

  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  private static final SimpleDateFormat SIMPLE_STR_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Date getDateFromStr(String dateString) throws ParseException {
    return SIMPLE_DATE_FORMAT.parse(dateString);
  }

  public static String getStrFromDate(Date date) {
    return SIMPLE_STR_FORMAT.format(date);
  }

  public static String getOnlyDateStrFromDate(Date date) {
    return SIMPLE_DATE_FORMAT.format(date);
  }
}
