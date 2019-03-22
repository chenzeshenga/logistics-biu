package com.abc.chenzeshenga.logistics.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * l_product_statistic
 *
 * @author chenz
 */
@Data public class ProductStatistic implements Serializable {
    private String sku;

    private String dysku;

    private String totalNum;

    private String onWayNum;

    private String uncheckNum;

    private String pendingPickupNum;

    private String pendingDeliverNum;

    private String defectNum;

    private Date statisticalTime;

}