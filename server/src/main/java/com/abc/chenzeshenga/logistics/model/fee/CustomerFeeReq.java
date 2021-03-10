package com.abc.chenzeshenga.logistics.model.fee;

import com.abc.chenzeshenga.logistics.model.common.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzeshenga
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerFeeReq extends Pagination {

    private String userId;
    private Integer startMonth;
    private Integer endMonth;

}
