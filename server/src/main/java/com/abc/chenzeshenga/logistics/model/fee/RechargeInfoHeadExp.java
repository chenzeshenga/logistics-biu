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
public class RechargeInfoHeadExp extends BaseRowModel {

    @ExcelProperty(index = 1, value = "uuid")
    private String uuid;
    @ExcelProperty(index = 2, value = "用户id")
    private String userId;
    @ExcelProperty(index = 3, value = "用户名称")
    private String nick;
    @ExcelProperty(index = 4, value = "充值日期")
    private String rechargeDate;
    @ExcelProperty(index = 5, value = "日元充值金额")
    private String amountInJpy;
    @ExcelProperty(index = 6, value = "人民币充值金额")
    private String amountInCny;
    @ExcelProperty(index = 7, value = "汇率")
    private String exchangeRate;
    @ExcelProperty(index = 8, value = "支付方式")
    private String payMethod;
    @ExcelProperty(index = 9, value = "备注")
    private String comments;

}
