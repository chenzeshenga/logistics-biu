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

import com.abc.chenzeshenga.logistics.model.amazon.ResponseHeaderMetadata;
import com.amazonservices.mws.client.MwsException;
import com.amazonservices.mws.client.MwsResponseHeaderMetadata;

/** Exceptions thrown by MarketplaceWebServiceOrders operations. */
public class MarketplaceWebServiceOrdersException extends MwsException {

  /** For serialization. */
  private static final long serialVersionUID = 1L;

  @Override
  public ResponseHeaderMetadata getResponseHeaderMetadata() {
    MwsResponseHeaderMetadata baseRHMD = super.getResponseHeaderMetadata();
    if (baseRHMD != null) {
      return new ResponseHeaderMetadata(baseRHMD);
    } else {
      return null;
    }
  }

  public void setResponseHeaderMetadata(ResponseHeaderMetadata rhmd) {
    super.setResponseHeaderMetadata(rhmd);
  }

  public MarketplaceWebServiceOrdersException(String message) {
    super(0, message, null);
  }

  public MarketplaceWebServiceOrdersException(String message, int statusCode) {
    super(statusCode, message, null);
  }

  public MarketplaceWebServiceOrdersException(Throwable t) {
    super(0, null, t);
  }

  public MarketplaceWebServiceOrdersException(String message, Throwable t) {
    super(0, message, t);
  }

  public MarketplaceWebServiceOrdersException(
      String message,
      int statusCode,
      String errorCode,
      String errorType,
      String requestId,
      String xml,
      ResponseHeaderMetadata rhmd) {
    super(statusCode, message, errorCode, errorType, xml, rhmd);
  }

  public MarketplaceWebServiceOrdersException(
      String message,
      int statusCode,
      String errorCode,
      String errorType,
      String requestId,
      String xml) {
    super(statusCode, message, errorCode, errorType, xml, null);
  }
}
