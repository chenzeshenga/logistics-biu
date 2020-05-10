package com.abc.service;

import com.abc.entity.SysUser;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

@SuppressWarnings("rawtypes")
public interface SysUserService extends IService<SysUser> {

  SysUser selectUserByUserName(String userName);

  Page<SysUser> queryUserIncludeRoles(Page page, String nick);

  Page<SysUser> queryUserIncludeRoles4Option(Page page, String nick, String username);

  int createUser(SysUser sysUser);
}
