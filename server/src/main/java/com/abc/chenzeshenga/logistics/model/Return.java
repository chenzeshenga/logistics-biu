package com.abc.chenzeshenga.logistics.model;

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

    private Date createOn;

    private Date updateOn;

    private String status;

    private String orderNo;

    private List<ReturnContent> contentList;

}
