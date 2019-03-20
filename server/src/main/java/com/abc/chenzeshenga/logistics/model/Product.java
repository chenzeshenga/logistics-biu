package com.abc.chenzeshenga.logistics.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Data public class Product implements Serializable {

    private String sku;

    private String productName = "";

    private int classification;

    private String classificationName;

    private String color = "";

    private String size = "";

    private double length;

    private double width;

    private double height;

    private double weight;

    private String createdBy = "";

    private String updateBy = "";

    private String productValue = "";

    private int alertNum;

    private String location = "";

    private String imgPath = "";

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date createOn;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8") private Date updateOn;

    private int avaNum;

    private String dySku;

    private byte[] img1;
    private byte[] img2;
    private byte[] img3;

}
