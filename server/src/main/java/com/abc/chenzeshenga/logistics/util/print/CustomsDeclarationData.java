package com.abc.chenzeshenga.logistics.util.print;

import com.deepoove.poi.config.Name;
import com.deepoove.poi.data.MiniTableRenderData;
import lombok.Data;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class CustomsDeclarationData {

    private MiniTableRenderData order;
    private String NO;
    private String ID;
    private String taitou;
    private String consignee;

    private String subtotal;
    private String tax;
    private String transform;
    private String other;
    private String unpay;
    private String total;

    /**
     * true data
     */
    private String deliverDate;
    private String trackNo;

    private String companyName;
    private String companyAddr;
    private String companyContact;
    private String phone;
    private String zipCode;

    private String toCompanyName;
    private String toCompanyAddr;
    private String toCompanyContact;
    private String toPhone;
    private String toZipCode;

    @Name("detailTable") private DetailData detailTable;
    private String totalPrice;

    private String date;
}
