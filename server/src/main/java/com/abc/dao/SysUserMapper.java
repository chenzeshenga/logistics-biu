package com.abc.dao;

import com.abc.entity.SysUser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper public interface SysUserMapper extends BaseMapper<SysUser> {

    List<SysUser> selectUserIncludeRoles(Pagination page, @Param("nick") String nick);

    List<SysUser> selectUserIncludeRoles4Option(Pagination page, @Param("nick") String nick, @Param("username") String username,
        @Param("roleValue") String roleValue);

}
