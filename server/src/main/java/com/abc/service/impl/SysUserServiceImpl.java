package com.abc.service.impl;

import com.abc.dao.SysUserMapper;
import com.abc.entity.SysUser;
import com.abc.service.SysUserService;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
@SuppressWarnings("rawtypes")
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService {

  @Override
  public Page<SysUser> queryUserIncludeRoles(Page page, String nick) {
    return page.setRecords(baseMapper.selectUserIncludeRoles(page, nick));
  }

  @Override
  public Page<SysUser> queryUserIncludeRoles4Option(Page page, String nick, String username) {
    return page.setRecords(baseMapper.selectUserIncludeRoles4Option(page, nick, username));
  }
}
