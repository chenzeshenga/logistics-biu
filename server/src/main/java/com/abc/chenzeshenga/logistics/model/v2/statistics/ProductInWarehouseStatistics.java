package com.abc.chenzeshenga.logistics.model.v2.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * represent l_product_statistics
 *
 * @author chenzeshenga
 * @since 2020-10-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductInWarehouseStatistics {

    private String uuid;
    private String dySku;
    private String productName;
    private String owner;
    private String shelfNo;
    private int totalNum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date statisticalTime;

    private Double volume;
    private Double weight;
}
