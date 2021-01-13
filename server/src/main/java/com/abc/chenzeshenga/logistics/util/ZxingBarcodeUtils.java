package com.abc.chenzeshenga.logistics.util;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import javax.imageio.ImageIO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chenzeshenga
 * @since 2020-08-28
 */
@Slf4j
public class ZxingBarcodeUtils {

  private ZxingBarcodeUtils() {}

  public static String decode(InputStream inputStream) {
    BufferedImage image;
    Result result;
    try {
      image = ImageIO.read(inputStream);
      LuminanceSource source = new BufferedImageLuminanceSource(image);
      BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
      result = new MultiFormatReader().decode(bitmap, null);
      log.info("barcode as {}", result.getText());
      return result.getText();
    } catch (Exception e) {
      log.error("error stack info ", e);
    }
    return null;
  }
}
