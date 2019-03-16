package com.abc.chenzeshenga.logistics.model;

import lombok.Data;
import lombok.NonNull;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Data public class ManualOrderContent implements Serializable {

    private String sku;

    private String name;

    private String num;

    private String price;

    private String ordno;

    private String picked;

    private boolean satisfied;

    private Double totalPrice;

    private String location;

}
