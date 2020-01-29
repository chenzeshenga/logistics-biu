package com.abc.chenzeshenga.logistics.model.user;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-29 22:51
 */
@TableName("sys_role")
@Data
public class CustSysRole {

    @TableId(type = IdType.ID_WORKER_STR)
    private String rid;
    private String rname;
    private String rdesc;
    private String rval;
    private boolean manager;
    private Date created;
    private Date updated;

}