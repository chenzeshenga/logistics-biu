package com.abc.chenzeshenga.logistics.model.fee;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author chenzeshenga
 */
@Data
@TableName("l_customer_account")
public class CustomerAccountInfo {

    private String uuid;
    private String userId;
    private String nick;
    private BigDecimal totalInJpy;
    private BigDecimal totalInCny;
    private BigDecimal feeInJpy;
    private BigDecimal feeInCny;
    private Date timestamp;

}
