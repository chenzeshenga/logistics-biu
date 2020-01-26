package com.abc.chenzeshenga.logistics.util.print;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.CompanyProfile;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.style.TableStyle;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STJc;
import org.springframework.stereotype.Component;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Component
public class CustomsDeclarationUtil {

  @Resource private ProductMapper productMapper;

  public void print(
      CompanyProfile from,
      CompanyProfile to,
      List<WarehousingContent> warehousingContentList,
      InputStream templateInputStream,
      OutputStream resultOutputStream)
      throws IOException {
    TableStyle rowStyle = new TableStyle();
    rowStyle.setAlign(STJc.CENTER);
    CustomsDeclarationData customsDeclarationData = new CustomsDeclarationData();
    customsDeclarationData.setDeliverDate(DateUtil.getOnlyDateStrFromDate(from.getDeliverDate()));
    customsDeclarationData.setTrackNo(from.getTrackNo());

    customsDeclarationData.setCompanyName(
        from.getEnglishName() + "(" + from.getChineseName() + ")");
    customsDeclarationData.setCompanyAddr(
        from.getEnglishAddr() + "(" + from.getChineseAddr() + ")");
    customsDeclarationData.setCompanyContact(
        from.getContactEnglishName() + "(" + from.getContactChineseName() + ")");
    customsDeclarationData.setPhone(from.getPhone());
    customsDeclarationData.setZipCode(from.getZipCode());

    customsDeclarationData.setToCompanyName(to.getEnglishName() + "(" + to.getChineseName() + ")");
    customsDeclarationData.setToCompanyAddr(to.getEnglishAddr() + "(" + to.getChineseAddr() + ")");
    customsDeclarationData.setToCompanyContact(
        to.getContactEnglishName() + "(" + to.getContactChineseName() + ")");
    customsDeclarationData.setToPhone(to.getPhone());
    customsDeclarationData.setToZipCode(to.getZipCode());

    List<RowRenderData> rowRenderDataList = new ArrayList<>();
    Double totalPrice = 0.0;
    for (WarehousingContent warehousingContent : warehousingContentList) {
      String sku = warehousingContent.getSku().split("/")[0];
      Product product = productMapper.selectByPrimaryKey(sku);
      warehousingContent.setPrice(product.getPrice());
      warehousingContent.setWeight(product.getWeight());
      RowRenderData rowRenderData =
          RowRenderData.build(
              warehousingContent.getName(),
              warehousingContent.getTotalNum(),
              warehousingContent.getPrice(),
              String.valueOf(
                  Double.valueOf(warehousingContent.getTotalNum())
                      * Double.valueOf(warehousingContent.getPrice())),
              warehousingContent.getWeight(),
              String.valueOf(
                  Double.valueOf(warehousingContent.getTotalNum())
                      * Double.valueOf(warehousingContent.getWeight())));
      totalPrice +=
          Double.valueOf(warehousingContent.getTotalNum())
              * Double.valueOf(warehousingContent.getPrice());
      rowRenderData.setRowStyle(rowStyle);
      rowRenderDataList.add(rowRenderData);
    }
    customsDeclarationData.setTotalPrice(String.valueOf(totalPrice));
    DetailData detailTable = new DetailData();
    detailTable.setGoods(rowRenderDataList);
    customsDeclarationData.setDetailTable(detailTable);

    customsDeclarationData.setDate(DateUtil.getOnlyDateStrFromDate(new Date()));

    Configure config =
        Configure.newBuilder().customPolicy("detailTable", new DetailTablePolicy()).build();
    XWPFTemplate template =
        XWPFTemplate.compile(templateInputStream, config).render(customsDeclarationData);
    template.write(resultOutputStream);
  }
}
