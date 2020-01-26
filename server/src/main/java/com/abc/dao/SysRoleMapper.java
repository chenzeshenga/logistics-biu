package com.abc.dao;

import com.abc.entity.SysRole;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

  List<SysRole> getRolesByUserId(@Param("userId") String userId);

  List<String> getRoleIdsByUserId(@Param("userId") String userId);

  Boolean checkRidsContainRval(@Param("rids") List<String> rids, @Param("rval") String rval);

  Boolean checkUidContainRval(@Param("uid") String uid, @Param("rval") String rval);
}
