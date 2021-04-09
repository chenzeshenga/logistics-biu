package com.abc.chenzeshenga.logistics.model.fee;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户充值信息
 *
 * @author chenzeshenga
 */
@Data
@TableName("l_recharge_info")
public class RechargeInfo {

    @TableId(type = IdType.UUID)
    private String uuid;
    private String userId;
    private String nick;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date rechargeDate;
    private BigDecimal amountInJpy;
    private BigDecimal amountInCny;
    private BigDecimal exchangeRate;
    private String payMethod;
    private String comments;

    private Date createdDate;

}
