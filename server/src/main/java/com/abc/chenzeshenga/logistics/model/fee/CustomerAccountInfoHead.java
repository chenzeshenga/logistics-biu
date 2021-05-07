package com.abc.chenzeshenga.logistics.model.fee;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chenzeshenga
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerAccountInfoHead extends BaseRowModel {

    @ExcelProperty(index = 1, value = "uuid")
    private String uuid;
    @ExcelProperty(index = 2, value = "用户id")
    private String userId;
    @ExcelProperty(index = 3, value = "昵称")
    private String nick;
    @ExcelProperty(index = 4, value = "日元余额")
    private BigDecimal totalInJpy;
    @ExcelProperty(index = 5, value = "人民币余额")
    private BigDecimal totalInCny;
    @ExcelProperty(index = 6, value = "日元费用")
    private BigDecimal feeInJpy;
    @ExcelProperty(index = 7, value = "人民币费用")
    private BigDecimal feeInCny;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;

}
