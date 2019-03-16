package com.abc.chenzeshenga.logistics.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import java.awt.image.BufferedImage;
import java.io.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j public class BarCodeUtil {

    public static File generateFile(String msg, String path, String category) {
        File file = new File(path);
        try {
            generate(msg, new FileOutputStream(file), category);
        } catch (FileNotFoundException e) {
            log.error("error stack info ", e);
        }
        return file;
    }

    public static byte[] generate(String msg, String category) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generate(msg, ous, category);
        return ous.toByteArray();
    }

    private static void generate(String msg, OutputStream ous, String category) {
        if (StringUtils.isEmpty(msg) || ous == null) {
            return;
        }
        AbstractBarcodeBean abstractBarcodeBean = null;
        if ("code128".equals(category)) {
            abstractBarcodeBean = new Code128Bean();
        } else if ("ean13".equals(category)) {
            abstractBarcodeBean = new EAN13Bean();
        }
        // 精细度
        final int dpi = 100;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(1.0f / dpi);
        // 配置对象
        abstractBarcodeBean.setModuleWidth(moduleWidth);
        abstractBarcodeBean.doQuietZone(false);
        String format = "image/jpeg";
        try {
            BitmapCanvasProvider canvas = new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            abstractBarcodeBean.generateBarcode(canvas, msg);
            canvas.finish();
        } catch (IOException e) {
            log.error("error stack info ", e);
        }
    }

}
