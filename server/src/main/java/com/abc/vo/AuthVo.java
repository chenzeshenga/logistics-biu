package com.abc.vo;

import lombok.*;

/**
 * created by CaiBaoHong at 2018/5/14 16:14<br>
 * 用于存储角色或权限的值
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthVo {

  // 显示名
  private String name;
  // 值
  private String val;
  private boolean manager;

  public AuthVo(String val) {
    this.val = val;
  }

  public AuthVo(String name, String val) {
    this.name = name;
    this.val = val;
  }
}
