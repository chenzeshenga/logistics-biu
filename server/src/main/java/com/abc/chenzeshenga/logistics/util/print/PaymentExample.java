package com.abc.chenzeshenga.logistics.util.print;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import org.junit.Test;

import java.io.FileOutputStream;

/**
 * 付款通知书：表格操作示例
 *
 * @author Sayi
 */
public class PaymentExample {

    CustomsDeclarationData datas = new CustomsDeclarationData();

    @Test
    public void testResumeExample() throws Exception {
        Configure config = Configure.newBuilder().customPolicy("detailTable", new DetailTablePolicy()).build();
        XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\chenz\\Desktop\\COMMERCIAL INVOICE.docx", config)
                .render(datas);
        FileOutputStream out = new FileOutputStream("out_付款通知书.docx");
        template.write(out);
        out.flush();
        out.close();
        template.close();
    }

}
