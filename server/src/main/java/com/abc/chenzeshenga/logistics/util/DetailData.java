package com.abc.chenzeshenga.logistics.util;

import com.deepoove.poi.data.RowRenderData;
import lombok.Data;

import java.util.List;

@Data public class DetailData {
    // 货品数据
    private List<RowRenderData> goods;

    // 人工费数据
    private List<RowRenderData> labors;

}
