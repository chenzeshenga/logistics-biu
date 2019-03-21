package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.awt.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Data public class Product implements Serializable, Cloneable {

    @NotEmpty(message = "商品sku必填") private String sku;
    private String dySku;
    @NotEmpty(message = "商品名称必填") private String productName;
    @NotEmpty(message = "商品类型必选") private String category;
    private String categoryName;
    private String color;
    private String size;
    private String length;
    private String width;
    private String height;
    private String weight;
    @NotEmpty(message = "商品价格必填") private String price;
    private String createdBy;
    private String updateBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;
    private int alertNum;
    private String location;
    private String imgPath;
    private String avaNum;
    private byte[] img1;
    private byte[] img2;
    private byte[] img3;

}
