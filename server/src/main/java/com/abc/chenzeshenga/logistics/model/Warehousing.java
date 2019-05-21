package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * l_warehousing
 *
 * @author
 */
@Data public class Warehousing implements Serializable {

    private String warehousingNo;

    private String target;

    private String method;

    private String methodDesc;

    private String carrier;

    private String carrierDesc;

    private String trackNo;

    private String deliverMethod;

    private String deliverMethodDesc;

    private String clearanceType;

    private String clearanceTypeDesc;

    private String taxType;

    private String taxTypeDesc;

    private String insurance;

    private String insuranceNum;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8") private Date estimatedDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;

    private String creator;

    private String updator;

    private String status;

    private String statusDesc;

    private List<WarehousingContent> warehousingContentList;

}
