package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;
import java.util.Date;

/**
 * l_product_statistic
 *
 * @author
 */
public class ProductStatistic implements Serializable {
    private String sku;

    private String dysku;

    private String totalNum;

    private String onWayNum;

    private String uncheckNum;

    private String pendingPickupNum;

    private String pendingDeliverNum;

    private String defectNum;

    private Date statisticalTime;

    private static final long serialVersionUID = 1L;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getDysku() {
        return dysku;
    }

    public void setDysku(String dysku) {
        this.dysku = dysku;
    }

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getOnWayNum() {
        return onWayNum;
    }

    public void setOnWayNum(String onWayNum) {
        this.onWayNum = onWayNum;
    }

    public String getUncheckNum() {
        return uncheckNum;
    }

    public void setUncheckNum(String uncheckNum) {
        this.uncheckNum = uncheckNum;
    }

    public String getPendingPickupNum() {
        return pendingPickupNum;
    }

    public void setPendingPickupNum(String pendingPickupNum) {
        this.pendingPickupNum = pendingPickupNum;
    }

    public String getPendingDeliverNum() {
        return pendingDeliverNum;
    }

    public void setPendingDeliverNum(String pendingDeliverNum) {
        this.pendingDeliverNum = pendingDeliverNum;
    }

    public String getDefectNum() {
        return defectNum;
    }

    public void setDefectNum(String defectNum) {
        this.defectNum = defectNum;
    }

    public Date getStatisticalTime() {
        return statisticalTime;
    }

    public void setStatisticalTime(Date statisticalTime) {
        this.statisticalTime = statisticalTime;
    }
}