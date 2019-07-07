package com.abc.chenzeshenga.logistics.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data @EqualsAndHashCode class ProductOnShelf implements Serializable {

    private String sku;
    private String dySku;
    private String name;
    private String location;
    private String num;

}
