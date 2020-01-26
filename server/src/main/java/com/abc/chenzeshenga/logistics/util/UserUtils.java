package com.abc.chenzeshenga.logistics.util;

import com.abc.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j
public class UserUtils {

  public static String getUserName() {
    Subject subject = SecurityUtils.getSubject();
    SysUser user = (SysUser) subject.getPrincipal();
    return user.getUname();
  }
}
