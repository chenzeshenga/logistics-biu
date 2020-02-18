/**
 * @file MwsOrdersConfig
 * @author chenzeshenga
 * @email chenzeshenga@163.com
 * @createOn 2018-10-6
 * @updateOn 2018-10-6
 * @version 1.0
 */
package com.abc.chenzeshenga.logistics.service.client;

import com.amazonservices.mws.client.MwsEndpoints;

public class MwsOrdersConfig {

//  AKIAIBNSITOXC4E6G4SQ
//
//  Hlk378HmiTqB6qNbpX9hcK/V3wE6n8uDwa6uXGq9
//
//    A2SNP3C6E0J094
//
//#   A1VC38T7YXB528
//
//  amzn.mws.46b82a85-7012-ba93-cf4f-6c084bbf1262


  private String accessKey;

  private String secretKey;

  private String appName = "DONGYUE";

  private String appVersion = "1.0";

  private String serviceUrl = MwsEndpoints.JP_PROD.toString();

  private MarketplaceWebServiceOrdersAsyncClient client = null;

  public MarketplaceWebServiceOrdersClient getClient() {
    return getAsyncClient();
  }

  public synchronized MarketplaceWebServiceOrdersAsyncClient getAsyncClient() {
    if (client == null) {
      MarketplaceWebServiceOrdersConfig config = new MarketplaceWebServiceOrdersConfig();
      config.setServiceURL(serviceUrl);
      client =
          new MarketplaceWebServiceOrdersAsyncClient(
              accessKey, secretKey, appName, appVersion, config, null);
    }
    return client;
  }

  public String getAccessKey() {
    return accessKey;
  }

  public void setAccessKey(String accessKey) {
    this.accessKey = accessKey;
  }

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public String toString() {
    return "MwsOrdersConfig{"
        + "accessKey='"
        + accessKey
        + '\''
        + ", secretKey='"
        + secretKey
        + '\''
        + ", appName='"
        + appName
        + '\''
        + ", appVersion='"
        + appVersion
        + '\''
        + ", serviceUrl='"
        + serviceUrl
        + '\''
        + '}';
  }
}
