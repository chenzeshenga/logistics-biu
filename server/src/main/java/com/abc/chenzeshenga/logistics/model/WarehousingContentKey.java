package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;

/**
 * l_warehousing_content
 *
 * @author
 */
public class WarehousingContentKey implements Serializable {
    private String warehousingNo;

    private String sku;

    private String boxSeq;

    private static final long serialVersionUID = 1L;

    public String getWarehousingNo() {
        return warehousingNo;
    }

    public void setWarehousingNo(String warehousingNo) {
        this.warehousingNo = warehousingNo;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getBoxSeq() {
        return boxSeq;
    }

    public void setBoxSeq(String boxSeq) {
        this.boxSeq = boxSeq;
    }
}