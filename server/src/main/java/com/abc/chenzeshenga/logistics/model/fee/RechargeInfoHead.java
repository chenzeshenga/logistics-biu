package com.abc.chenzeshenga.logistics.model.fee;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户充值信息
 *
 * @author chenzeshenga
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class RechargeInfoHead extends BaseRowModel {

    @ExcelProperty(index = 0, value = "用户id(具体id可参考sheet2)")
    private String userId;
    @ExcelProperty(index = 1, value = "充值日期(格式为YYYYMMdd,如20210101)")
    private String rechargeDate;
    @ExcelProperty(index = 2, value = "日元充值金额")
    private String amountInJpy;
    @ExcelProperty(index = 3, value = "人民币充值金额")
    private String amountInCny;
    @ExcelProperty(index = 4, value = "汇率")
    private String exchangeRate;
    @ExcelProperty(index = 5, value = "支付方式")
    private String payMethod;
    @ExcelProperty(index = 6, value = "备注")
    private String comments;

}
