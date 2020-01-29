package com.abc.chenzeshenga.logistics.model.user;

import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author chenzeshenga
 * @since 2020-01-29 22:32
 */
@TableName("sys_user_role")
@Data
public class UserRoleMapping {

    private String userId;
    private String roleId;
}