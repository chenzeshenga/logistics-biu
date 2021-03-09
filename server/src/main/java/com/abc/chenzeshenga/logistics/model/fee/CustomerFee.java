package com.abc.chenzeshenga.logistics.model.fee;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chenzeshenga
 */
@Data
@TableName("l_customer_fee")
public class CustomerFee {

    @TableId(type = IdType.UUID)
    private String uuid;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 费用期间
     */
    private String relatedMonth;
    /**
     * 货币
     */
    private String currency;
    /**
     * 入库查询-接收退货手续费
     */
    private BigDecimal fee1;
    /**
     * 代付到付查询-代付到付&手续费
     */
    private BigDecimal fee2;
    /**
     * 出库查询-一件代发-一件代发
     */
    private BigDecimal fee3;
    /**
     * 出库查询-FBA转仓-FBA转仓
     */
    private BigDecimal fee4;
    /**
     * 仓储查询-仓储费
     */
    private BigDecimal fee5;
    /**
     * 其他费用-检测等-其他费用
     */
    private BigDecimal fee6;
    /**
     * 头程查询-头程费用
     */
    private BigDecimal fee7;
    /**
     * 转运回国-转运回国费用
     */
    private BigDecimal fee8;
    /**
     * 月租&客服费用
     */
    private BigDecimal fee9;
    /**
     * 备注
     */
    private String comments;
    /**
     * 账单文件uuid
     */
    private String fileUuid;
}
