package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class ManualOrder implements Serializable {

    @NotEmpty(message = "订单类型必选") private String category;
    private String categoryName;
    private String channel;
    private String channelDesc;
    private String chinaNo;
    @NotEmpty(message = "订单内容必选") @JsonProperty("contentList") private List<ManualOrderContent> manualOrderContents;
    private String collect;
    private String fromContact;
    private String fromDetailAddress;
    private String fromName;
    private String fromZipCode;
    /**
     * pk
     */
    @NotEmpty(message = "订单号必填，且长度大于3个字符") @Size(min = 3, max = 255) private String orderNo;
    /**
     * selected from address
     */
    private List<String> selectedAddress;
    @JsonProperty("selectedtoAddress") private List<String> selectedToAddress;
    private String toContact;
    @NotEmpty(message = "收件人地址必填") private String toDetailAddress;
    @NotEmpty(message = "收件人姓名必填") private String toName;
    @NotEmpty(message = "收件人邮编必填") private String toZipCode;
    private String trackNo;
    private String fromKenId;
    private String fromCityId;
    private String fromTownId;
    private String fromAddressDesc;
    private String toKenId;
    private String toCityId;
    private String toTownId;
    private String toAddressDesc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;
    private String creator;
    private String updator;
    private String status;
    private String statusDesc;
    private String carrierNo;
    private String carrierName;
    private String amount;
    private String fromKenName;
    private String fromCityName;
    private String fromTownName;
    private String toKenName;
    private String toCityName;
    private String toTownName;
    private Map<String, String> address;
    private Map<String, String> toAddress;
    private String abnormalReason;

}
