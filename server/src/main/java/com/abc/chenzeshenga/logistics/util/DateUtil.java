package com.abc.chenzeshenga.logistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class DateUtil {

  private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
  private static final SimpleDateFormat SIMPLE_DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd");

  private static final SimpleDateFormat SIMPLE_STR_FORMAT =
      new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  public static Date getDateFromStr(String dateString) throws ParseException {
    return SIMPLE_DATE_FORMAT.parse(dateString);
  }

  public static Date getDateFromStr2(String dateString) throws ParseException {
    return SIMPLE_DATE_FORMAT_2.parse(dateString);
  }

  public static String getStrFromDate(Date date) {
    return SIMPLE_STR_FORMAT.format(date);
  }

  public static String getOnlyDateStrFromDate(Date date) {
    return SIMPLE_DATE_FORMAT.format(date);
  }

  public static String getDatePoor(Date endDate, Date nowDate) {
    long nd = 1000 * 24 * 60 * 60;
    long nh = 1000 * 60 * 60;
    long nm = 1000 * 60;
    long diff = endDate.getTime() - nowDate.getTime();
    long day = diff / nd;
    long hour = diff % nd / nh;
    long min = diff % nd % nh / nm;
    return day + "天" + hour + "小时" + min + "分钟";
  }

  public static int getHourPoor(Date endDate, Date startDate) {
    long nd = 1000 * 24 * 60 * 60;
    long nh = 1000 * 60 * 60;
    long diff = endDate.getTime() - startDate.getTime();
    long day = diff / nd;
    long hour = diff % nd / nh;
    return (int) (day * 24 + hour);
  }

  public static String generateDatePattern4Amazon(Date date) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC+0"));
    return simpleDateFormat.format(date);
  }
}
