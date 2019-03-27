package com.abc.chenzeshenga.logistics.model;

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

    private String carrier;

    private String trackNo;

    private String deliverMethod;

    private String clearanceType;

    private String taxType;

    private String insurance;

    private String insuranceNum;

    private Date estimatedDate;

    private Date createOn;

    private Date updateOn;

    private String creator;

    private String updator;

    private String status;

    private List<WarehousingContent> warehousingContentList;

}