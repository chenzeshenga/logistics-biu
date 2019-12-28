package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 2019.07.20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnContent implements Serializable {

    private static final long serialVersionUID = 999405990725459271L;

    private String returnNo;

    private String sku;

    private String name;

    private String num;

    private String dealWith;

}
