/**
 * @file StoreInfoReq
 * @author chenzeshenga
 * @email chenzeshenga@163.com
 * @createOn 2018-10-5
 * @updateOn 2018-10-5
 * @version 1.0
 */
package com.abc.chenzeshenga.logistics.model.amazon;

import lombok.Data;

import java.io.Serializable;

@Data public class StoreInfoReq implements Serializable {

  private int id;

  private String userId;

  private String username;

  private String sellerId;

  private String mwsAuthToken;

  private String secretKey;

  private int platform;

  private String platformDesc;

  private String enable;

  private String awsAccessKey;

}
