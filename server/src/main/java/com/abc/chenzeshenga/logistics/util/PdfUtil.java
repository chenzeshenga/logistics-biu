package com.abc.chenzeshenga.logistics.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class PdfUtil {

    public static byte[] skuPdf() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        return outputStream.toByteArray();
    }

}
