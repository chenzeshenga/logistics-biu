package com.abc.dao;

import com.abc.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectUserByUserName(String userName);

    List<SysUser> selectUserIncludeRoles(Pagination page, @Param("nick") String nick);

    List<SysUser> selectUserIncludeRoles4Option(
        Pagination page, @Param("nick") String nick, @Param("username") String username);

    int createUser(SysUser sysUser);

    @Override
    Integer updateById(SysUser sysUser);

    SysUser selectById(String uid);

    /**
     * 获取所有用户信息
     *
     * @return 用户列表
     */
    List<SysUser> selectAll();

}
