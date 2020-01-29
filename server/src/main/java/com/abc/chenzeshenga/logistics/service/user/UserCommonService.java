package com.abc.chenzeshenga.logistics.service.user;

import com.abc.chenzeshenga.logistics.model.user.CustSysRole;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.abc.chenzeshenga.logistics.model.user.UserRoleMapping;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-01-29 23:17
 */
@Service
public class UserCommonService {

  private UserService userService;

  private UserRoleService userRoleService;

  private RoleService roleService;

  @Autowired
  public UserCommonService(
      UserService userService, UserRoleService userRoleService, RoleService roleService) {
    this.userService = userService;
    this.userRoleService = userRoleService;
    this.roleService = roleService;
  }

  public boolean isManagerRole(String userName) {
    CustSysUser custSysUser = userService.fetchUser(userName);
    Map<String, Object> columnMap = new HashMap<>(1);
    columnMap.put("user_id", custSysUser.getUid());
    List<UserRoleMapping> roleList = userRoleService.selectByMap(columnMap);
    for (int i = 0; i < roleList.size(); i++) {
      UserRoleMapping userRoleMapping = roleList.get(i);
      String roleId = userRoleMapping.getRoleId();
      Map<String, Object> roleColumnMap = new HashMap<>(1);
      roleColumnMap.put("rid", roleId);
      List<CustSysRole> custSysRoles = roleService.selectByMap(roleColumnMap);
      for (int j = 0; j < custSysRoles.size(); j++) {
        CustSysRole custSysRole = custSysRoles.get(i);
        if (custSysRole.isManager()) {
          return true;
        }
      }
    }
    return false;
  }
}
