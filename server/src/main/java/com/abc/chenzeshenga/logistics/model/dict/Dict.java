package com.abc.chenzeshenga.logistics.model.dict;

import lombok.Data;

import java.util.Date;

/**
 * @author chenzeshenga
 * @since 2020-12-01 21:43
 */
@Data
public class Dict {

    private Integer id;
    private String key;
    private String value;
    private Date ctime;

}
