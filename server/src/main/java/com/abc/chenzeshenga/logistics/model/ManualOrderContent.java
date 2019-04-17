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

    private String num = "0";

    private String price;

    private String ordno;

    private String picked = "0";

    private boolean satisfied;

    private Double totalPrice;

    private String location;

    private String imgUrl;

}
