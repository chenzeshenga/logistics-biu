package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Data @AllArgsConstructor @NoArgsConstructor public class Return implements Serializable {

    private String returnNo;

    private boolean withoutOrderNoFlag;

    private String orderNo;

    private String status;

    private String toName;
    private String toContact;
    private String toZipCode;
    private String toDetailAddress;
    private String toKenId;
    private String toCityId;
    private String toTownId;

    private String fromName;
    private String fromContact;
    private String fromZipCode;
    private String fromDetailAddress;
    private String fromKenId;
    private String fromCityId;
    private String fromTownId;

    private String carrier;

    private String trackNo;

    private String imgs;

    private String creator;

    private String updator;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;

    private List<ReturnContent> contentList;

}
