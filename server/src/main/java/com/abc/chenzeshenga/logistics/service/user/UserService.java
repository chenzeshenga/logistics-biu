package com.abc.chenzeshenga.logistics.service.user;

import javax.annotation.Resource;

import com.abc.chenzeshenga.logistics.mapper.user.CustSysUserMapper;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;

import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-29 20:48
 */
@Service
public class UserService {

    @Resource
    private CustSysUserMapper custSysUserMapper;

    public CustSysUser fetchUser(String userId) {
        return custSysUserMapper.fetchUserByUserName(userId);
    }

}