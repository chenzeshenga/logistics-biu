package com.abc.chenzeshenga.logistics.model.user;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-29 21:54
 */
@TableName("sys_user")
@Data
public class CustSysUser {

    @TableId(type = IdType.ID_WORKER_STR)
    private String uid;
    private String uname;
    private String nick;
    private String pwd;
    private String salt;
    private Boolean lock;
    private Date created;
    private Date updated;

}