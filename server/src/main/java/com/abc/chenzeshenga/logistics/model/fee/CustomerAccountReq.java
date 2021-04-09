package com.abc.chenzeshenga.logistics.model.fee;

import com.abc.chenzeshenga.logistics.model.common.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author chenzeshenga
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CustomerAccountReq extends Pagination {

    private String userId;
}


