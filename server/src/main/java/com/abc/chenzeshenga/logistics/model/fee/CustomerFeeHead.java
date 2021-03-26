package com.abc.chenzeshenga.logistics.model.fee;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author chenzeshenga
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerFeeHead extends BaseRowModel {

    /**
     * 用户id
     */
    @ExcelProperty(index = 1, value = "用户id(具体id可参考sheet2)")
    private String userId;
    /**
     * 费用期间
     */
    @ExcelProperty(index = 2, value = "费用期间(格式为YYYYMM,如202103)")
    private Integer relatedMonth;
    /**
     * 货币
     */
    @ExcelProperty(index = 3, value = "货币")
    private String currency;
    /**
     * 入库查询-接收退货手续费
     */
    @ExcelProperty(index = 4, value = "退货手续费")
    private BigDecimal fee1;
    /**
     * 代付到付查询-代付到付&手续费
     */
    @ExcelProperty(index = 5, value = "代付到付手续费")
    private BigDecimal fee2;
    /**
     * 出库查询-一件代发-一件代发
     */
    @ExcelProperty(index = 6, value = "一件代发")
    private BigDecimal fee3;
    /**
     * 出库查询-FBA转仓-FBA转仓
     */
    @ExcelProperty(index = 7, value = "FBA转仓")
    private BigDecimal fee4;
    /**
     * 仓储查询-仓储费
     */
    @ExcelProperty(index = 8, value = "仓储费")
    private BigDecimal fee5;
    /**
     * 其他费用-检测等-其他费用
     */
    @ExcelProperty(index = 9, value = "检测等其他费用")
    private BigDecimal fee6;
    /**
     * 头程查询-头程费用
     */
    @ExcelProperty(index = 10, value = "头程费用")
    private BigDecimal fee7;
    /**
     * 转运回国-转运回国费用
     */
    @ExcelProperty(index = 11, value = "转运回国费用")
    private BigDecimal fee8;
    /**
     * 月租&客服费用
     */
    @ExcelProperty(index = 12, value = "月租&客服费用")
    private BigDecimal fee9;
    /**
     * 备注
     */
    @ExcelProperty(index = 13, value = "备注")
    private String comments;

}
