package com.abc.chenzeshenga.logistics.model;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class TrackNo {

    private int id;
    @NotEmpty(message = "承运人必选") private String carrier;
    private String carrierDesc;
    @NotEmpty(message = "最小追踪单号必填") private Long min;
    @NotEmpty(message = "最大追踪单号必填") private Long max;

}
