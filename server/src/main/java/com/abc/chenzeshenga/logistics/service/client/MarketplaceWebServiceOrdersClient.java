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
import com.abc.chenzeshenga.logistics.model.amazon.MwsResponse;
import com.amazonservices.mws.client.*;

public class MarketplaceWebServiceOrdersClient implements MarketplaceWebServiceOrders {

  private static final String LIBRARY_NAME = "MarketplaceWebServiceOrders";

  private static final String LIBRARY_VERSION = "2018-08-01";

  protected String servicePath;

  protected final MwsConnection connection;

  public MarketplaceWebServiceOrdersClient(
      String accessKey,
      String secretKey,
      String applicationName,
      String applicationVersion,
      MarketplaceWebServiceOrdersConfig config) {
    connection = config.copyConnection();
    connection.setAwsAccessKeyId(accessKey);
    connection.setAwsSecretKeyId(secretKey);
    connection.setApplicationName(applicationName);
    connection.setApplicationVersion(applicationVersion);
    connection.setLibraryVersion(LIBRARY_VERSION);
    servicePath = config.getServicePath();
  }

  public MarketplaceWebServiceOrdersClient(
      String accessKey, String secretKey, MarketplaceWebServiceOrdersConfig config) {
    this(accessKey, secretKey, LIBRARY_NAME, LIBRARY_VERSION, config);
  }

  public MarketplaceWebServiceOrdersClient(
      String accessKey, String secretKey, String applicationName, String applicationVersion) {
    this(
        accessKey,
        secretKey,
        applicationName,
        applicationVersion,
        new MarketplaceWebServiceOrdersConfig());
  }

  @Override
  public GetOrderResponse getOrder(GetOrderRequest request) {
    return connection.call(
        new RequestType("GetOrder", GetOrderResponse.class, servicePath), request);
  }

  @Override
  public GetServiceStatusResponse getServiceStatus(GetServiceStatusRequest request) {
    return connection.call(
        new RequestType("GetServiceStatus", GetServiceStatusResponse.class, servicePath), request);
  }

  @Override
  public ListOrderItemsResponse listOrderItems(ListOrderItemsRequest request) {
    return connection.call(
        new RequestType("ListOrderItems", ListOrderItemsResponse.class, servicePath), request);
  }

  @Override
  public ListOrderItemsByNextTokenResponse listOrderItemsByNextToken(
      ListOrderItemsByNextTokenRequest request) {
    return connection.call(
        new RequestType(
            "ListOrderItemsByNextToken", ListOrderItemsByNextTokenResponse.class, servicePath),
        request);
  }

  @Override
  public ListOrdersResponse listOrders(ListOrdersRequest request) {
    return connection.call(
        new RequestType("ListOrders", ListOrdersResponse.class, servicePath), request);
  }

  @Override
  public ListOrdersByNextTokenResponse listOrdersByNextToken(ListOrdersByNextTokenRequest request) {
    return connection.call(
        new RequestType("ListOrdersByNextToken", ListOrdersByNextTokenResponse.class, servicePath),
        request);
  }

  public static String quoteAppName(String s) {
    return MwsUtl.escapeAppName(s);
  }

  public static String quoteAppVersion(String s) {
    return MwsUtl.escapeAppVersion(s);
  }

  public static String quoteAttributeName(String s) {
    return MwsUtl.escapeAttributeName(s);
  }

  public static String quoteAttributeValue(String s) {
    return MwsUtl.escapeAttributeValue(s);
  }

  protected static class RequestType implements MwsRequestType {

    private final String operationName;
    private final Class<? extends MwsResponse> responseClass;
    private final String servicePath;

    public RequestType(
        String operationName, Class<? extends MwsResponse> responseClass, String servicePath) {
      this.operationName = operationName;
      this.responseClass = responseClass;
      this.servicePath = servicePath;
    }

    @Override
    public String getServicePath() {
      return this.servicePath;
    }

    @Override
    public String getOperationName() {
      return this.operationName;
    }

    @Override
    public Class<? extends MwsObject> getResponseClass() {
      return this.responseClass;
    }

    @Override
    public MwsException wrapException(Throwable cause) {
      return new MarketplaceWebServiceOrdersException(cause);
    }

    @Override
    public void setRHMD(MwsObject response, MwsResponseHeaderMetadata rhmd) {
      ((MwsResponse) response).setResponseHeaderMetadata(new ResponseHeaderMetadata(rhmd));
    }
  }
}
