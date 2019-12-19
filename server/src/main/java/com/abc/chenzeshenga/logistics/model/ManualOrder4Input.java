package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ManualOrder4Input extends BaseRowModel implements Serializable {

    @ExcelProperty(index = 0, value = "订单类型")
    private String categoryName;
    @ExcelProperty(index = 1, value = "渠道类型")
    private String channelCode;
    @ExcelProperty(index = 2, value = "发件地址-道/府/县")
    private String fromKenName;
    @ExcelProperty(index = 3, value = "发件地址-城市")
    private String fromCityName;
    @ExcelProperty(index = 4, value = "发件地址-乡镇")
    private String fromTownName;
    @ExcelProperty(index = 5, value = "发件地址")
    private String fromDetailAddress;
    @ExcelProperty(index = 6, value = "发件地址邮编")
    private String fromZipCode;
    @ExcelProperty(index = 7, value = "发件人姓名")
    private String fromName;
    @ExcelProperty(index = 8, value = "发件人联系方式")
    private String fromContact;
    @ExcelProperty(index = 9, value = "收件地址-道/府/县")
    private String toKenName;
    @ExcelProperty(index = 10, value = "收件地址-城市")
    private String toCityName;
    @ExcelProperty(index = 11, value = "收件地址-乡镇")
    private String toTownName;
    @ExcelProperty(index = 12, value = "收件地址")
    private String toDetailAddress;
    @ExcelProperty(index = 13, value = "收件地址邮编")
    private String toZipCode;
    @ExcelProperty(index = 14, value = "收件人姓名")
    private String toName;
    @ExcelProperty(index = 15, value = "收件人联系方式")
    private String toContact;
    @ExcelProperty(index = 16, value = "是否代收费用")
    private String collectDesc;
    @ExcelProperty(index = 17, value = "代收费用总额(JPY)")
    private String collectNum;
    @ExcelProperty(index = 18, value = "sku")
    private String sku1;
    @ExcelProperty(index = 19, value = "名称")
    private String name1;
    @ExcelProperty(index = 20, value = "数量")
    private String num1;
    @ExcelProperty(index = 21, value = "sku")
    private String sku2;
    @ExcelProperty(index = 22, value = "名称")
    private String name2;
    @ExcelProperty(index = 23, value = "数量")
    private String num2;
    @ExcelProperty(index = 24, value = "sku")
    private String sku3;
    @ExcelProperty(index = 25, value = "名称")
    private String name3;
    @ExcelProperty(index = 26, value = "数量")
    private String num3;

}
