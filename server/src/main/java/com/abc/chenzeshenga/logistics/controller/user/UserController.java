package com.abc.chenzeshenga.logistics.controller.user;

import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 * @since 2020-05-23 21:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

  private UserCommonService userCommonService;

  @Autowired
  public UserController(UserCommonService userCommonService) {
    this.userCommonService = userCommonService;
  }

  @GetMapping("/isManagerRole")
  public Json isManagerRole() {
    return Json.succ().data(userCommonService.isManagerRole(UserUtils.getUserName()));
  }

  @GetMapping("/whoami")
  public Json whoami() {
    return Json.succ().data(UserUtils.getUserName());
  }
}
