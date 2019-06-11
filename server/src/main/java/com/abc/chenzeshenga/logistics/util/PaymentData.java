package com.abc.chenzeshenga.logistics.util;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.MiniTableRenderData;
import lombok.Data;

@Data public class PaymentData {

    private MiniTableRenderData order;
    private String NO;
    private String ID;
    private String taitou;
    private String consignee;
    @Name("detail_table") private DetailData detailTable;
    private String subtotal;
    private String tax;
    private String transform;
    private String other;
    private String unpay;
    private String total;

    private String deliverDate;
    private String trackNo;
    private String totalPrice;

}
