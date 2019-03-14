package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class ManualOrder implements Serializable {

    private String category;
    private String categoryName;
    private String channel;
    private String channelDesc;
    private String chinaNo;
    @JsonProperty("contentList") private List<ManualOrderContent> manualOrderContents;
    private String collect;
    private String fromContact;
    private String fromDetailAddress;
    private String fromName;
    private String fromZipCode;
    /**
     * pk
     */
    private String orderNo;
    /**
     * selected from address
     */
    private List<String> selectedAddress;
    @JsonProperty("selectedtoAddress") private List<String> selectedToAddress;
    private String toContact;
    private String toDetailAddress;
    private String toName;
    private String toZipCode;
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
    private String amount;
    private String fromKenName;
    private String fromCityName;
    private String fromTownName;
    private String toKenName;
    private String toCityName;
    private String toTownName;
    private Map<String, String> address;
    private Map<String, String> toAddress;
}
