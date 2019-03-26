package com.abc.chenzeshenga.logistics.model;

import java.io.Serializable;

/**
 * l_warehousing_content
 *
 * @author
 */
public class WarehousingContent extends WarehousingContentKey implements Serializable {
    private String totalNum;

    private String wrapType;

    private String name;

    private static final long serialVersionUID = 1L;

    public String getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(String totalNum) {
        this.totalNum = totalNum;
    }

    public String getWrapType() {
        return wrapType;
    }

    public void setWrapType(String wrapType) {
        this.wrapType = wrapType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}