package com.abc.chenzeshenga.logistics.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file JpDetailAddress
 * @email chenzeshenga@163.com
 * @createOn 2018-12-31 10:16
 * @updateOn 2018-12-31 10:16
 */
@Data public class JpDetailAddress implements Serializable {

    private String kenId;

    private String kenName;

    private String cityId;

    private String cityName;

    private String townId;

    private String townName;

}
