package com.abc.dao;

import com.abc.entity.SysPerm;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysPermMapper extends BaseMapper<SysPerm> {

  List<SysPerm> getPermsByUserId(@Param("userId") String userId);

  List<SysPerm> getPermsByRoleId(@Param("roleId") String roleId);

  void saveOrUpdate(@Param("perms") List<SysPerm> perms);
}
