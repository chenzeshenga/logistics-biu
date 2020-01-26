package com.abc.chenzeshenga.logistics.util;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class PdfUtil {

  private PdfUtil() {}

  public static byte[] skuPdf(String sku, String name) throws IOException {
    // barcode image
    Image image = new Image(ImageDataFactory.create(BarCodeUtil.generate(sku)));
    ByteArrayOutputStream byteArrayOutputStreamResult = new ByteArrayOutputStream();
    PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStreamResult);
    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
    Document document = new Document(pdfDocument, new PageSize(135, 135));
    document.setMargins(5, 0, 0, 0);
    PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
    // 1列的表格
    Table table = new Table(new float[] {1});
    table.setAutoLayout();
    table
        .setVerticalAlignment(VerticalAlignment.MIDDLE)
        .setHorizontalAlignment(HorizontalAlignment.CENTER);
    table.setBorder(Border.NO_BORDER);
    image.setHorizontalAlignment(HorizontalAlignment.CENTER);
    Cell imgCell = new Cell();
    imgCell.add(image);
    imgCell.setBorder(Border.NO_BORDER);
    table.addCell(imgCell);
    Cell labelCell = new Cell();
    labelCell.setBorder(Border.NO_BORDER);
    Paragraph paragraph1 =
        new Paragraph(
            new Text("sku: " + sku)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBold()
                .setFontSize(10)
                .setFont(font));
    Paragraph paragraph2 =
        new Paragraph(
            new Text("名称: " + name)
                .setBold()
                .setFontSize(10)
                .setFont(font)
                .setHorizontalAlignment(HorizontalAlignment.CENTER));
    paragraph1.setMarginLeft(5);
    paragraph2.setMarginLeft(5);
    labelCell.add(paragraph1).add(paragraph2);
    table.addCell(labelCell);
    document.add(table);
    document.close();
    return byteArrayOutputStreamResult.toByteArray();
  }

  public static byte[] skuBarCode(String sku, String name) throws IOException {
    PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    Image image = new Image(ImageDataFactory.create(BarCodeUtil.generate(sku, "code128")));
    image.setMargins(0, 0, 0, 0);
    PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStream);
    PdfDocument pdfDocument = new PdfDocument(pdfWriter);
    Document document = new Document(pdfDocument, new PageSize(135, 135));
    document.setMargins(10, 10, 10, 10);
    document.add(image);
    Table table = new Table(1);
    Cell labelCell = new Cell();
    labelCell.setBorder(Border.NO_BORDER);
    Paragraph paragraph1 =
        new Paragraph(
            new Text("sku: " + sku)
                .setHorizontalAlignment(HorizontalAlignment.CENTER)
                .setBold()
                .setFontSize(10)
                .setFont(font));
    Paragraph paragraph2 =
        new Paragraph(
            new Text("名称: " + name)
                .setBold()
                .setFontSize(10)
                .setFont(font)
                .setHorizontalAlignment(HorizontalAlignment.CENTER));
    paragraph1.setMarginLeft(5);
    paragraph2.setMarginLeft(5);
    labelCell.add(paragraph1).add(paragraph2);
    table.addCell(labelCell);
    document.add(table);
    document.close();
    return byteArrayOutputStream.toByteArray();
  }
}
