package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.UserInfoMapper;
import com.abc.chenzeshenga.logistics.model.UserInfo;
import com.abc.vo.Json;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 2019-12-28
 */
@Slf4j
@RestController
@RequestMapping("/user/info")
public class UserInfoController {

  private UserInfoMapper userInfoMapper;

  @Autowired
  public UserInfoController(UserInfoMapper userInfoMapper) {
    this.userInfoMapper = userInfoMapper;
  }

  @PostMapping("/get")
  public Json getUserInfoByUserId(@RequestBody Map<String, String> request) {
    String userId = request.get("userId");
    UserInfo userInfo = userInfoMapper.getUserInfoById(userId);
    return Json.succ("userInfo", userInfo);
  }
}
