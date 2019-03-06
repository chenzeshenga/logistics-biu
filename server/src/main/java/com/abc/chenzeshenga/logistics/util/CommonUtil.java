package com.abc.chenzeshenga.logistics.util;

import java.util.UUID;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file CommonUtil
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 21:59
 * @updateOn 2019-3-6 21:59
 */
public class CommonUtil {

    public static String generate() {
        return "DY" + UUID.randomUUID().toString().replace("-", "");
    }

}
