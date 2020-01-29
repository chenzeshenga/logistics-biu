package com.abc.chenzeshenga.logistics.service.user;

import com.abc.chenzeshenga.logistics.mapper.user.CustSysUserMapper;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-29 20:48
 */
@Service
public class UserService extends ServiceImpl<CustSysUserMapper, CustSysUser> {

  public CustSysUser fetchUser(String userId) {
    return baseMapper.fetchUserByUserName(userId);
  }

}
