package com.abc.chenzeshenga.logistics.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.krysalis.barcode4j.impl.AbstractBarcodeBean;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.EnumMap;
import java.util.Map;

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

    public static byte[] generate(String msg) {
        ByteArrayOutputStream ous = new ByteArrayOutputStream();
        generateQrCode(msg, ous);
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
        final int dpi = 1024;
        // module宽度
        final double moduleWidth = UnitConv.in2mm(2.6f / dpi);
        // 配置对象
        abstractBarcodeBean.setModuleWidth(moduleWidth);
        abstractBarcodeBean.setHeight(5);
        abstractBarcodeBean.setFontSize(0.8);
        abstractBarcodeBean.doQuietZone(false);
        String format = "image/jpeg";
        try {
            BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(ous, format, dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
            abstractBarcodeBean.generateBarcode(canvas, msg);
            canvas.finish();
        } catch (IOException e) {
            log.error("error stack info ", e);
        }
    }

    /**
     * @param content      内容
     * @param outputStream 输出流
     * @reference https://crunchify.com/java-simple-qr-code-generator-example/
     */
    private static void generateQrCode(String content, OutputStream outputStream) {
        int size = 70;
        String fileType = "png";
        try {
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
            hintMap.put(EncodeHintType.MARGIN, 1);
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, size, size, hintMap);
            int width = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            image.createGraphics();
            Graphics2D graphics = (Graphics2D)image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image, fileType, outputStream);
        } catch (WriterException | IOException e) {
            log.error("error stack info", e);
        }
    }

    public static void test() {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File("D://test.png");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(BarCodeUtil.generate("8184645419778"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void test2() {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            file = new File("D://test2.jpeg");
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(BarCodeUtil.generate("8184645419778", "code128"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        BarCodeUtil.test();
        BarCodeUtil.test2();
    }

}
