package com.abc.chenzeshenga.logistics.service;

import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;

/**
 * @author chenzeshenga
 * @since 2020-02-07 23:29:00
 */
public interface AmazonOrderService {

  /**
   * 手动同步来自亚马逊的订单信息
   *
   * @param createAfterStr
   * @param createBeforeStr
   * @throws MarketplaceWebServiceOrdersException
   */
  void syncOrders(String createAfterStr, String createBeforeStr)
      throws MarketplaceWebServiceOrdersException;

  /**
   * 自动同步来自亚马逊的订单信息
   *
   * @throws MarketplaceWebServiceOrdersException
   */
  void syncOrdersAuto() throws MarketplaceWebServiceOrdersException;
}
