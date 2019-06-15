package com.abc.chenzeshenga.logistics.model;

import lombok.Data;

import java.io.Serializable;

/**
 * l_warehousing_content
 *
 * @author chenzesheng
 */
@Data public class WarehousingContent extends WarehousingContentKey implements Serializable {
    private String totalNum;

    private String wrapType;

    private String name;

    private String price;

    private String weight;

}
