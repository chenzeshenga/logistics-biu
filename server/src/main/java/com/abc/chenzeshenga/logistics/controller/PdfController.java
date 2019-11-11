package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.util.BarCodeUtil;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.PdfUtil;
import com.abc.chenzeshenga.logistics.util.StringUtil;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @Slf4j @RequestMapping("/pdf") public class PdfController {

    @Resource private OrderMapper orderMapper;

    @Resource private ProductMapper productMapper;

    @GetMapping("/ord/{ordno}") public void pdf(HttpServletResponse response, @PathVariable String ordno)
        throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        List<ManualOrderContent> manualOrderContentList = orderMapper.listContent2(ordno);
        PdfWriter writer = new PdfWriter(outputStream);
        generatePdfV2(ordno, manualOrderContentList, writer);
        response.setContentType("application/pdf");
        response.getOutputStream().write(outputStream.toByteArray());
    }

    private void generatePdfV1(@PathVariable String ordno, List<ManualOrderContent> manualOrderContentList,
        PdfWriter writer) throws IOException {
        try (PdfDocument pdf = new PdfDocument(writer)) {
            try (Document document = new Document(pdf, PageSize.A7)) {
                document.setMargins(5, 5, 5, 5);
                PdfFont font = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
                Paragraph head = new Paragraph().add("东岳物流").setFont(font).addStyle(new Style().setMarginLeft(30));
                document.add(head);
                Paragraph time =
                    new Paragraph().add(DateUtil.getStrFromDate(new Date())).addStyle(new Style().setMarginLeft(50));
                document.add(time);
                Image barcode = new Image(ImageDataFactory.create(BarCodeUtil.generate(ordno, "code128")));
                Paragraph paragraph =
                    new Paragraph().add(barcode).addStyle(new Style().setVerticalAlignment(VerticalAlignment.MIDDLE))
                        .addStyle(new Style().setHorizontalAlignment(HorizontalAlignment.CENTER));
                document.add(paragraph);
                Table table = new Table(new float[] {8, 4, 4});
                table.setWidth(UnitValue.createPercentValue(100));
                table.addHeaderCell(new Cell().add(new Paragraph("商品名称")).setFont(font))
                    .addHeaderCell(new Cell().add(new Paragraph("货架位置")).setFont(font))
                    .addHeaderCell(new Cell().add(new Paragraph("商品数量")).setFont(font));
                manualOrderContentList.forEach(manualOrderContent -> table.addCell(
                    new Cell().add(new Paragraph(StringUtil.correctString(manualOrderContent.getName()))).setFont(font))
                    .addCell(new Cell().add(new Paragraph(StringUtil.correctString(manualOrderContent.getLocation())))
                        .setFont(font)).addCell(
                        new Cell().add(new Paragraph(StringUtil.correctString(manualOrderContent.getNum())))
                            .setFont(font)));
                document.add(table);
            }
        }
    }

    private void generatePdfV2(@PathVariable String ordno, List<ManualOrderContent> manualOrderContentList,
        PdfWriter writer) throws IOException {
        try (PdfDocument pdf = new PdfDocument(writer)) {
            try (Document document = new Document(pdf, new PageSize(141, 141))) {
                document.setMargins(3, 3, 3, 3);
                PdfFont font = PdfFontFactory.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", false);
                Paragraph head = new Paragraph().add("東岳配信QRコード").setFont(font).addStyle(new Style().setMarginLeft(15));
                document.add(head);
                Image barcode = new Image(
                    ImageDataFactory.create(BarCodeUtil.generate("http://www.jpdyu.com/#/order-info?ord=" + ordno)));
                Paragraph paragraph = new Paragraph().add(barcode).addStyle(new Style().setMarginLeft(30));
                document.add(paragraph);
                Paragraph ordnoTitle = new Paragraph().add(new Text("お問い合わせ番号: " + ordno).setFontSize(6)).setFont(font)
                    .addStyle(new Style().setMarginLeft(5));
                document.add(ordnoTitle);
            }
        }
    }

    @GetMapping("/sku/{dySku}") public void skuPdf(HttpServletResponse response, @PathVariable String dySku)
        throws IOException {
        response.setContentType("application/pdf");
        Product product = productMapper.selectByPrimaryKey(dySku);
        response.getOutputStream().write(PdfUtil.skuBarCode(dySku, product.getProductName()));
    }

}
