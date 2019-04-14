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

    private PdfUtil() {
    }

    public static byte[] skuPdf(String sku, String name) throws IOException {
        //barcode image
        Image image = new Image(ImageDataFactory.create(BarCodeUtil.generate(sku)));
        ByteArrayOutputStream byteArrayOutputStreamResult = new ByteArrayOutputStream();
        PdfWriter pdfWriter = new PdfWriter(byteArrayOutputStreamResult);
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument, new PageSize(135, 135));
        document.setMargins(0, 0, 0, 0);
        PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
        //1列的表格
        Table table = new Table(new float[] {1});
        table.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE);
        table.setBorder(Border.NO_BORDER);
        table.addCell(new Cell().setVerticalAlignment(VerticalAlignment.MIDDLE).setHorizontalAlignment(HorizontalAlignment.CENTER).add(image)
            .setBorder(Border.NO_BORDER));
        Cell labelCell = new Cell();
        labelCell.setHorizontalAlignment(HorizontalAlignment.CENTER).setVerticalAlignment(VerticalAlignment.MIDDLE)
            .add(new Paragraph(new Text(sku).setBold().setFontSize(10)));
        if (name.length() > 6) {
            name = name.substring(0, 6) + "...";
        }
        labelCell.add(new Paragraph(new Text(name).setBold().setFontSize(10))).setFont(font).setBorder(Border.NO_BORDER);
        table.addCell(labelCell);
        document.add(table);
        document.close();
        return byteArrayOutputStreamResult.toByteArray();
    }

}
