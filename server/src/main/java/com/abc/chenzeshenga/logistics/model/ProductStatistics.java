package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * l_product_statistic
 *
 * @author chenz
 */
@Data public class ProductStatistics implements Serializable {
    private String sku;

    private String dysku;

    private String owner;

    private String totalNum;

    private String onWayNum;

    private String uncheckNum;

    private String pendingPickupNum;

    private String pendingDeliverNum;

    private String defectNum;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date statisticalTime = new Date();

}