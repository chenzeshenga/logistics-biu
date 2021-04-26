package com.abc.chenzeshenga.logistics.model.ord;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzeshenga
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ManualOrderHead extends BaseRowModel {

    @ExcelProperty(index = 1, value = "订单号")
    private String orderNo;

    @ExcelProperty(index = 2, value = "订单费用(JPY)")
    private String ordUserFee;

}
