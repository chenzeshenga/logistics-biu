package com.abc.chenzeshenga.logistics.model;

import com.abc.chenzeshenga.logistics.model.claim.ClaimContentDealing;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.7.19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("l_returning")
@JsonIgnoreProperties(value = {"handler"})
public class Return implements Serializable {

    private static final long serialVersionUID = -6249013466703476021L;

    @TableId(type = IdType.ID_WORKER_STR)
    private String returnNo;

    @TableField(value = "create_by")
    private String creator;
    private boolean withoutOrderNoFlag;
    private String toName;
    private String orderNo;
    private String toContact;
    private String toZipCode;
    private String fromName;
    private String carrier;
    private String fromContact;
    private String fromZipCode;
    private String trackNo;
    private String toAddressLine1;
    private String toAddressLine2;
    private String toAddressLine3;
    private String toDetailAddressLbl;
    private String chinaCarrier;
    private String imgs;
    private String status;

    private String fromAddressLine1;
    private String fromAddressLine2;
    private String fromAddressLine3;
    private String fromDetailAddressLbl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createOn;
    private String createOnLbl;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateOn;


    @TableField(value = "update_by")
    private String updator;


    private List<ReturnContent> contentList;

    private List<ClaimContentDealing> contentDealingList;

    private Date fromDate = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);

    private Date endDate = new Date(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);

    private String type;
}
