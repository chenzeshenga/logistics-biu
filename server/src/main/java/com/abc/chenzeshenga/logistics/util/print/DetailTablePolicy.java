package com.abc.chenzeshenga.logistics.util.print;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.MiniTableRenderPolicy;
import java.util.List;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

/**
 * @author chenzeshenga
 * @version 1.0
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

  @Override
  public void render(XWPFTable table, Object data) {
    if (null == data) {
      return;
    }
    DetailData detailData = (DetailData) data;
    List<RowRenderData> goods = detailData.getGoods();
    if (null != goods) {
      table.removeRow(2);
      // 循环插入行
      for (RowRenderData good : goods) {
        XWPFTableRow insertNewTableRow = table.insertNewTableRow(2);
        for (int j = 0; j < 6; j++) {
          insertNewTableRow.createCell();
        }
        MiniTableRenderPolicy.Helper.renderRow(table, 2, good);
      }
    }
  }
}
