package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file Channel
 * @email chenzeshenga@163.com
 * @createOn 2019-3-6 0:29
 * @updateOn 2019-3-6 0:29
 */
@Data public class Channel implements Serializable {

    private int id;

    private String channelCode;

    private String channelName;

    private String channelAdapter;

    private String cooperatorCode;

    private String cooperatorName;

    private String currencyCode;

    private String currencyName;

    private int minDays;

    private int maxDays;

    private int weightRuleCode;

    private String weightRuleDesc;

    private int weightMulti;

    private double defaultPackageWeight;

    private double defaultPackageFee;

    private int feeRuleCode;

    private String feeRuleName;

    private String whetherMultiPackage;

    private String whetherVolume;

    private String whetherChargeAtFirst;

    private String whetherChargeForThem;

    private String whetherInsurance;

    private String enable;

    private String remarks;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;

    private String createBy;

    private String updateBy;

}
