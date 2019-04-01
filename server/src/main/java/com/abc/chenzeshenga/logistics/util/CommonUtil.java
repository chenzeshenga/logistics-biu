package com.abc.chenzeshenga.logistics.util;

import java.util.UUID;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class CommonUtil {

    private CommonUtil() {
    }

    public static String generate() {
        return "DY" + UUID.randomUUID().toString().split("-")[0];
    }

}
