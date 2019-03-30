package com.abc.chenzeshenga.logistics.model;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data @EqualsAndHashCode(callSuper = false) public class ManualOrder extends BaseRowModel implements Serializable {

    /**
     * pk
     */
    @NotEmpty(message = "订单号必填，且长度大于3个字符") @Size(min = 3, max = 255) @ExcelProperty(index = 0, value = "订单号") private String orderNo;
    @ExcelProperty(index = 1, value = "当前状态") private String statusDesc;
    @ExcelProperty(index = 2, value = "创建者") private String creator;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") @ExcelProperty(index = 3, value = "创建时间", format = "yyyy-MM-dd HH:mm:ss")
    private Date createOn;
    @ExcelProperty(index = 4, value = "承运人") private String carrierName;
    @ExcelProperty(index = 5, value = "追踪单号") private String trackNo;
    @ExcelProperty(index = 6, value = "订单类型") private String categoryName;
    @ExcelProperty(index = 7, value = "发件地址-道/府/县") private String fromKenName;
    @ExcelProperty(index = 8, value = "发件地址-城市") private String fromCityName;
    @ExcelProperty(index = 9, value = "发件地址-乡镇") private String fromTownName;
    @ExcelProperty(index = 10, value = "发件详细地址") private String fromDetailAddress;
    @ExcelProperty(index = 11, value = "发件地址邮编") private String fromZipCode;
    @ExcelProperty(index = 12, value = "发件人姓名") private String fromName;
    @ExcelProperty(index = 13, value = "发件人联系方式") private String fromContact;
    @ExcelProperty(index = 14, value = "收件地址-道/府/县") private String toKenName;
    @ExcelProperty(index = 15, value = "收件地址-城市") private String toCityName;
    @ExcelProperty(index = 16, value = "收件地址-乡镇") private String toTownName;
    @ExcelProperty(index = 17, value = "收件详细地址") @NotEmpty(message = "收件人地址必填") private String toDetailAddress;
    @ExcelProperty(index = 18, value = "收件地址邮编") @NotEmpty(message = "收件人邮编必填") private String toZipCode;
    @ExcelProperty(index = 19, value = "收件人姓名") @NotEmpty(message = "收件人姓名必填") private String toName;
    @ExcelProperty(index = 20, value = "收件人联系方式") private String toContact;
    @ExcelProperty(index = 21, value = "是否代收费用") private String collect;
    private String status;
    private String carrierNo;
    private String chinaCarrier;
    private String updator;
    @NotEmpty(message = "订单类型必选") private String category;
    private String channel;
    private String channelDesc;
    private String chinaNo;
    @NotEmpty(message = "订单内容必选") @JsonProperty("contentList") private List<ManualOrderContent> manualOrderContents;
    /**
     * selected from address
     */
    private List<String> selectedAddress;
    @JsonProperty("selectedtoAddress") private List<String> selectedToAddress;
    private String fromKenId;
    private String fromCityId;
    private String fromTownId;
    private String fromAddressDesc;
    private String toKenId;
    private String toCityId;
    private String toTownId;
    private String toAddressDesc;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;
    private String amount;
    private Map<String, String> address;
    private Map<String, String> toAddress;
    private String abnormalReason;
    private String totalVolume;
    private String totalWeight;
    private String ordFee;
    private boolean satisfied = true;

}
