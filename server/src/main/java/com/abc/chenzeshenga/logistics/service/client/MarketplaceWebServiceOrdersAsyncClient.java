/**
 * ***************************************************************************** Copyright 2009-2018
 * Amazon Services. All Rights Reserved. Licensed under the Apache License, Version 2.0 (the
 * "License");
 *
 * <p>You may not use this file except in compliance with the License. You may obtain a copy of the
 * License at: http://aws.amazon.com/apache2.0 This file is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * ****************************************************************************** Marketplace Web
 * Service Orders API Version: 2013-09-01 Library Version: 2018-08-01 Generated: Wed Aug 29 10:45:06
 * PDT 2018
 */
package com.abc.chenzeshenga.logistics.service.client;

import com.abc.chenzeshenga.logistics.model.amazon.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * @author amazon
 * @version 1.0
 * @file MarketplaceWebServiceOrdersAsyncClient
 * @createOn 2019-3-1 20:46
 * @updateOn 2019-3-1 20:46
 */
public class MarketplaceWebServiceOrdersAsyncClient extends MarketplaceWebServiceOrdersClient
    implements MarketplaceWebServiceOrdersAsync {

  public MarketplaceWebServiceOrdersAsyncClient(
      String accessKey,
      String secretKey,
      String applicationName,
      String applicationVersion,
      MarketplaceWebServiceOrdersConfig config,
      ExecutorService executor) {
    super(accessKey, secretKey, applicationName, applicationVersion, config);
    connection.setExecutorService(executor);
  }

  public MarketplaceWebServiceOrdersAsyncClient(
      String accessKey,
      String secretKey,
      String applicationName,
      String applicationVersion,
      MarketplaceWebServiceOrdersConfig config) {
    super(accessKey, secretKey, applicationName, applicationVersion, config);
  }

  public MarketplaceWebServiceOrdersAsyncClient(
      String accessKey, String secretKey, MarketplaceWebServiceOrdersConfig config) {
    super(accessKey, secretKey, config);
  }

  public MarketplaceWebServiceOrdersAsyncClient(
      String accessKey, String secretKey, String applicationName, String applicationVersion) {
    super(accessKey, secretKey, applicationName, applicationVersion);
  }

  @Override
  public Future<GetOrderResponse> getOrderAsync(GetOrderRequest request) {
    return connection.callAsync(
        new RequestType("GetOrder", GetOrderResponse.class, servicePath), request);
  }

  @Override
  public Future<GetServiceStatusResponse> getServiceStatusAsync(GetServiceStatusRequest request) {
    return connection.callAsync(
        new RequestType("GetServiceStatus", GetServiceStatusResponse.class, servicePath), request);
  }

  @Override
  public Future<ListOrderItemsResponse> listOrderItemsAsync(ListOrderItemsRequest request) {
    return connection.callAsync(
        new RequestType("ListOrderItems", ListOrderItemsResponse.class, servicePath), request);
  }

  @Override
  public Future<ListOrderItemsByNextTokenResponse> listOrderItemsByNextTokenAsync(
      ListOrderItemsByNextTokenRequest request) {
    return connection.callAsync(
        new RequestType(
            "ListOrderItemsByNextToken", ListOrderItemsByNextTokenResponse.class, servicePath),
        request);
  }

  @Override
  public Future<ListOrdersResponse> listOrdersAsync(ListOrdersRequest request) {
    return connection.callAsync(
        new RequestType("ListOrders", ListOrdersResponse.class, servicePath), request);
  }

  @Override
  public Future<ListOrdersByNextTokenResponse> listOrdersByNextTokenAsync(
      ListOrdersByNextTokenRequest request) {
    return connection.callAsync(
        new RequestType("ListOrdersByNextToken", ListOrdersByNextTokenResponse.class, servicePath),
        request);
  }
}
