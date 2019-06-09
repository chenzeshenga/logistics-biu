package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class WarehousingReq {

    private String warehousingNo;
    private String creator;
    private String channelCode;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") private Date from;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") private Date to;

}
