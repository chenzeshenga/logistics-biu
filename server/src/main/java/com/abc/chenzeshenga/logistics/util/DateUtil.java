package com.abc.chenzeshenga.logistics.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file DateUtil
 * @email chenzeshenga@163.com
 * @createOn 2019-3-15 22:34
 * @updateOn 2019-3-15 22:34
 */
public class DateUtil {

    private final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static Date getDateFromStr(String dateString) throws ParseException {
        return SIMPLE_DATE_FORMAT.parse(dateString);
    }
}
