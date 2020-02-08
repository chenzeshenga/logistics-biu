package com.abc.chenzeshenga.logistics.mapper.user;

import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author chenzeshenga
 * @since 2020-01-29 21:51
 */
@Mapper
public interface CustSysUserMapper extends BaseMapper<CustSysUser> {

  CustSysUser fetchUserByUserName(String userName);
}
