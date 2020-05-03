package com.abc.entity;

import com.abc.vo.AuthVo;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import java.io.Serializable;
import java.util.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** created by CaiBaoHong at 2018/4/17 14:55<br> */
@TableName("sys_user")
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUser extends Model<SysUser> {

  private static final long serialVersionUID = -9196678993144944443L;

  // 用户id
  @TableId(type = IdType.ID_WORKER_STR)
  private String uid;

  // 登录名，不可改
  private String uname;
  // 用户昵称，可改
  private String nick;
  // 已加密的登录密码
  private String pwd;
  // 加密盐值
  private String salt;
  // 是否锁定
  private Boolean lock;
  private Boolean isManagerRole;
  // 创建时间
  private Date created;
  // 修改时间
  private Date updated;

  @TableField(exist = false)
  private List<SysRole> roleList = new ArrayList<>(); // 用户所有角色值，在管理后台显示用户的角色

  @TableField(exist = false)
  private Set<AuthVo> roles = new HashSet<>(); // 用户所有角色值，用于shiro做角色权限的判断

  @TableField(exist = false)
  private Set<AuthVo> perms = new HashSet<>(); // 用户所有权限值，用于shiro做资源权限的判断

  @Override
  protected Serializable pkVal() {
    return uid;
  }
}
