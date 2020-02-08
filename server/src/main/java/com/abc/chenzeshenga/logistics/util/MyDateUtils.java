package com.abc.chenzeshenga.logistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file MyDateUtils
 * @email chenzeshenga@163.com
 * @createOn 2018-11-3
 * @updateOn 2018-11-3
 */
public class MyDateUtils {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd");

    public static Calendar getCalendarFromStr(String dateString) throws ParseException {
        Date date = SIMPLE_DATE_FORMAT.parse(dateString);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;

    }

}
