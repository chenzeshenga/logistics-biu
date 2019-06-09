package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 */
@Data public class Warehousing implements Serializable {

    private String target;
    private String warehousingNo;
    /**
     * 头程方式
     */
    private String method;
    /**
     * 头程承运人
     */
    private String carrier;
    /**
     * 承运人追踪号
     */
    private String trackNo;
    /**
     * 头程渠道
     */
    private String channel;
    /**
     * 渠道描述
     */
    private String channelDesc;
    /**
     * 运输方式
     */
    private String deliverMethod;
    /**
     * 报关类型
     */
    private String clearanceType;
    /**
     * 关税类型
     */
    private String taxType;
    /**
     * 保险服务 Y/N
     */
    private String insurance;
    /**
     * 保险金额
     */
    private String insuranceNum;
    /**
     * 预计到港时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") private Date estimatedDate;

    private List<WarehousingContent> warehousingContentList;

    private String status;

    private String statusDesc;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;

    private String creator;

    private String updator;

}
