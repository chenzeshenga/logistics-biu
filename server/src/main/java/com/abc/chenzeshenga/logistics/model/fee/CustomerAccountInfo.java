package com.abc.chenzeshenga.logistics.model.fee;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date timestamp;

}
