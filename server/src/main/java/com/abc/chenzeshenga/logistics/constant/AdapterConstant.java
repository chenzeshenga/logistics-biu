package com.abc.chenzeshenga.logistics.constant;

/**
 * @author chenz
 */
public class AdapterConstant {

    private static final String ADAPTER_1 = "入库单";
    private static final String ADAPTER_2 = "单票单清";
    private static final String ADAPTER_3 = "虚拟海外仓";
    private static final String ADAPTER_4 = "海外仓代发";

    public static String getAdapter(String adapterId) {
        switch (adapterId) {
            case "2":
                return ADAPTER_2;
            case "3":
                return ADAPTER_3;
            case "4":
                return ADAPTER_4;
            case "1":
            default:
                return ADAPTER_1;
        }
    }

}

