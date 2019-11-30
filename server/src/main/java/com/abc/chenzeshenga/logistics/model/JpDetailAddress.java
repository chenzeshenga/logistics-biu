package com.abc.chenzeshenga.logistics.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190819
 */
@Data @ToString public class JpDetailAddress implements Serializable {

    private String kenId;

    private String kenName;

    private String cityId;

    private String cityName;

    private String townId;

    private String townName;

    private String zip;

}
