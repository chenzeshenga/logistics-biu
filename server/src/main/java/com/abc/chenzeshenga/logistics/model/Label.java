package com.abc.chenzeshenga.logistics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author chenzeshenga
 * @version 1.0
 * @file Label
 * @email chenzeshenga@163.com
 * @createOn 2018-12-21 0:58
 * @updateOn 2018-12-21 0:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Label implements Serializable {

    private static final long serialVersionUID = -2553893219114259851L;

    private int id;

    private int seq;

    private String key;

    private String value;

    private String label;

    public Label(int seq, String key, String value) {
        this.seq = seq;
        this.key = key;
        this.value = value;
    }

}
