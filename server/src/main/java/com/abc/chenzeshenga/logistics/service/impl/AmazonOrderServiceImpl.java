package com.abc.chenzeshenga.logistics.service.impl;

import com.abc.chenzeshenga.logistics.model.amazon.*;
import com.abc.chenzeshenga.logistics.service.AmazonOrderService;
import com.abc.chenzeshenga.logistics.service.client.MarketplaceWebServiceOrdersClient;
import com.abc.chenzeshenga.logistics.service.client.MarketplaceWebServiceOrdersException;
import com.abc.chenzeshenga.logistics.service.client.MwsOrdersConfig;
import com.abc.chenzeshenga.logistics.util.MyDateUtils;
import com.amazonservices.mws.client.MwsUtl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chenzeshenga
 * @since 2020-02-07 23:30:00
 */
//@Service
//@EnableScheduling
@Slf4j
public class AmazonOrderServiceImpl implements AmazonOrderService {

  @Override public void syncOrders(String createAfterStr, String createBeforeStr)
    throws MarketplaceWebServiceOrdersException {
    return;
  }

  @Override
//  @PostConstruct
  public void syncOrdersAuto() throws MarketplaceWebServiceOrdersException {
    StoreInfoReq infoReq = new StoreInfoReq();
    infoReq.setSellerId("AOYEK60LBAPEN");
    infoReq.setMwsAuthToken("amzn.mws.28b80f58-0fbc-9f83-leaf-9a75cdc7b295");
    infoReq.setSecretKey("SIOhohQJmYcqwCXGgP9t5ZlK9XoTQ3Dp0xWyUUsE");
    infoReq.setAwsAccessKey("AKIAJ7YDUM5NCBSW4ODA");
    List<StoreInfoReq> storeInfoReqList = new ArrayList<>();
    storeInfoReqList.add(infoReq);
    for (StoreInfoReq storeInfoReq : storeInfoReqList) {
      MwsOrdersConfig config = new MwsOrdersConfig();
      String userId = storeInfoReq.getUserId();
      //      String username = userService.getUsernameByUserId(userId);
      String username = "";
      config.setSecretKey(storeInfoReq.getSecretKey());
      config.setAccessKey(storeInfoReq.getMwsAuthToken());
      MarketplaceWebServiceOrdersClient client = config.getClient();

      ListOrdersRequest listOrdersRequest = new ListOrdersRequest();
      listOrdersRequest.setSellerId(storeInfoReq.getSellerId());
      listOrdersRequest.setMWSAuthToken(storeInfoReq.getMwsAuthToken());
      XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      createdAfter.setYear(calendar.get(Calendar.YEAR));
      createdAfter.setMonth(calendar.get(Calendar.MONTH) + 1);
      createdAfter.setDay(calendar.get(Calendar.DAY_OF_MONTH));
      listOrdersRequest.setCreatedAfter(createdAfter);

      XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
      calendar.add(Calendar.DAY_OF_YEAR, 1);
      createdBefore.setYear(calendar.get(Calendar.YEAR));
      createdBefore.setMonth(calendar.get(Calendar.MONTH) + 1);
      createdBefore.setDay(calendar.get(Calendar.DAY_OF_MONTH));
      listOrdersRequest.setCreatedBefore(createdBefore);

      try {
        ListOrdersResponse response = new ListOrdersResponse();
        response = client.listOrders(listOrdersRequest);
        ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
        String nextToken = response.getListOrdersResult().getNextToken();
        List<Order> orders = new ArrayList<Order>();
        orders.addAll(response.getListOrdersResult().getOrders());
        if (!StringUtils.isEmpty(nextToken)) {
          List<Order> temp = getNextOrderList(client, nextToken, storeInfoReq);
          orders.addAll(temp);
        }
        log.info("list order RequestId --> {}, Timestamp --> {}", rhmd.getRequestId(), rhmd.getTimestamp());
        log.info("response original msg as \r\n {}", response.toXMLFragment());

        for (Order order : orders) {
          storeSaleRecord(username, storeInfoReq, client, order);
        }
      } catch (MarketplaceWebServiceOrdersException ex) {
        log.error("error stack info ", ex);
      }
    }
    for (StoreInfoReq storeInfoReq : storeInfoReqList) {
      MwsOrdersConfig config = new MwsOrdersConfig();
      String userId = storeInfoReq.getUserId();
      //      String username = userService.getUsernameByUserId(userId);
      String username = "";
      config.setSecretKey(storeInfoReq.getSecretKey());
      config.setAccessKey(storeInfoReq.getAwsAccessKey());
      MarketplaceWebServiceOrdersClient client = config.getClient();

      ListOrdersRequest listOrdersRequest = new ListOrdersRequest();
      listOrdersRequest.setSellerId(storeInfoReq.getSellerId());
      listOrdersRequest.setMWSAuthToken(storeInfoReq.getMwsAuthToken());
      XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_YEAR, -1);
      createdAfter.setYear(calendar.get(Calendar.YEAR));
      createdAfter.setMonth(calendar.get(Calendar.MONTH) + 1);
      createdAfter.setDay(calendar.get(Calendar.DAY_OF_MONTH));
      listOrdersRequest.setCreatedAfter(createdAfter);

      XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
      calendar.add(Calendar.DAY_OF_YEAR, 1);
      createdBefore.setYear(calendar.get(Calendar.YEAR));
      createdBefore.setMonth(calendar.get(Calendar.MONTH) + 1);
      createdBefore.setDay(calendar.get(Calendar.DAY_OF_MONTH));
      listOrdersRequest.setCreatedBefore(createdBefore);

      try {
        ListOrdersResponse response = new ListOrdersResponse();
        response = client.listOrders(listOrdersRequest);
        ResponseHeaderMetadata rhmd = response.getResponseHeaderMetadata();
        String nextToken = response.getListOrdersResult().getNextToken();
        List<Order> orders = new ArrayList<Order>();
        orders.addAll(response.getListOrdersResult().getOrders());
        if (!StringUtils.isEmpty(nextToken)) {
          List<Order> temp = getNextOrderList(client, nextToken, storeInfoReq);
          orders.addAll(temp);
        }
        log.info("list order RequestId --> {}, Timestamp --> {}", rhmd.getRequestId(), rhmd.getTimestamp());
        log.info("response original msg as \r\n {}", response.toXMLFragment());

        for (Order order : orders) {
          storeSaleRecord(username, storeInfoReq, client, order);
        }
      } catch (MarketplaceWebServiceOrdersException ex) {
        log.error("error stack info ", ex);
      }
    }
  }

  private void storeSaleRecord(String username, StoreInfoReq storeInfoReq, MarketplaceWebServiceOrdersClient client,
    Order order) {
    ListOrderItemsRequest listOrderItemsRequest = new ListOrderItemsRequest();
    listOrderItemsRequest.setSellerId(storeInfoReq.getSellerId());
    listOrderItemsRequest.setMWSAuthToken(storeInfoReq.getMwsAuthToken());
    listOrderItemsRequest.setAmazonOrderId(order.getAmazonOrderId());

    ListOrderItemsResponse listOrderItemsResponse = client.listOrderItems(listOrderItemsRequest);
    ResponseHeaderMetadata responseHeaderMetadata = listOrderItemsResponse.getResponseHeaderMetadata();

    log.info("list order item RequestId --> {}, Timestamp --> {}, AmazonOrderId --> {}",
      responseHeaderMetadata.getRequestId(), responseHeaderMetadata.getTimestamp(),
      listOrderItemsRequest.getAmazonOrderId());
    log.info("list order item original msg as \r\n {}", listOrderItemsResponse.toXMLFragment());
    OrderItem orderItem = listOrderItemsResponse.getListOrderItemsResult().getOrderItems().get(0);
    //    SaleRecord saleRecord = new SaleRecord();
    //
    //    saleRecord.setSaleRecNo(order.getAmazonOrderId());
    //    saleRecord.setSku(orderItem.getSellerSKU());
    //    saleRecord.setFromUserId(username);
    //    Address address = order.getShippingAddress();
    //    if (address != null) {
    //      saleRecord.setToContact(StringUtils.isEmpty(address.getPhone()) ? "" : address.getPhone());
    //      saleRecord.setToPostCode(StringUtils.isEmpty(address.getPostalCode()) ? "" : address.getPostalCode());
    //      String stateOrRegion = StringUtils.isEmpty(address.getStateOrRegion()) ? "" : address.getStateOrRegion();
    //      String addressLine1 = StringUtils.isEmpty(address.getAddressLine1()) ? "" : address.getAddressLine1();
    //      saleRecord.setToWhere(stateOrRegion + "--" + addressLine1);
    //      saleRecord.setToWho(address.getName());
    //    }
    //    Money money = order.getOrderTotal();
    //    if (money != null) {
    //      saleRecord.setAmountOnbehalf(order.getOrderTotal().getCurrencyCode() + "--" + order.getOrderTotal().getAmount());
    //    }
    //    saleRecord.setName(orderItem.getTitle());
    //    saleRecord.setNum(String.valueOf(order.getNumberOfItemsUnshipped()));
    //    saleRecord.setCreator(username);
    //    saleRecord.setCreateOn(new Date());
    //    saleRecord.setStatus(SeqConstant.ONE);
    //    saleService.insert(saleRecord);
    //    log.info("order {} was synchronized", saleRecord);
  }

  //  private void syncOrderManual(AtomicReference<ListOrdersResponse> response, String username, String createAfterStr,
  //    String createBeforeStr) throws ParseException {
  //    StoreInfoReq info = userService.getStoreInfoByUsername(username);
  //    MwsOrdersConfig config = new MwsOrdersConfig();
  //    config.setSecretKey(info.getSecretKey());
  //    config.setAccessKey(info.getMwsAuthToken());
  //    Calendar dateAfter = MyDateUtils.getCalendarFromStr(createAfterStr);
  //    Calendar dateBefore = MyDateUtils.getCalendarFromStr(createBeforeStr);
  //    MarketplaceWebServiceOrdersClient client = config.getClient();
  //
  //    ListOrdersRequest listOrdersRequest = new ListOrdersRequest();
  //    listOrdersRequest.setSellerId(info.getSellerId());
  //    listOrdersRequest.setMWSAuthToken(info.getMwsAuthToken());
  //    XMLGregorianCalendar createdAfter = MwsUtl.getDTF().newXMLGregorianCalendar();
  //
  //    createdAfter.setYear(dateAfter.get(Calendar.YEAR));
  //    createdAfter.setMonth(dateAfter.get(Calendar.MONTH));
  //    createdAfter.setDay(dateAfter.get(Calendar.DAY_OF_MONTH));
  //    listOrdersRequest.setCreatedAfter(createdAfter);
  //    XMLGregorianCalendar createdBefore = MwsUtl.getDTF().newXMLGregorianCalendar();
  //    createdBefore.setYear(dateBefore.get(Calendar.YEAR));
  //    createdBefore.setMonth(dateBefore.get(Calendar.MONTH));
  //    createdBefore.setDay(dateBefore.get(Calendar.DAY_OF_MONTH));
  //    listOrdersRequest.setCreatedBefore(createdBefore);
  //    try {
  //      response.set(client.listOrders(listOrdersRequest));
  //      ResponseHeaderMetadata rhmd = response.get().getResponseHeaderMetadata();
  //      String nextToken = response.get().getListOrdersResult().getNextToken();
  //      List<Order> orders = new ArrayList<Order>();
  //      if (!StringUtils.isEmpty(nextToken)) {
  //        List<Order> temp = getNextOrderList(client, nextToken, info);
  //        orders.addAll(temp);
  //      }
  //      log.info("list order RequestId --> {}, Timestamp --> {}", rhmd.getRequestId(), rhmd.getTimestamp());
  //      log.info("response original msg as \r\n {}", response.get().toXMLFragment());
  //
  //      for (Order order : orders) {
  //        storeSaleRecord(username, info, client, order);
  //      }
  //    } catch (MarketplaceWebServiceOrdersException ex) {
  //      log.error(ex.getMessage());
  //    }
  //  }

  private static List<Order> getNextOrderList(MarketplaceWebServiceOrdersClient client, String nextToken,
    StoreInfoReq info) {
    ListOrdersByNextTokenRequest listOrdersRequest = new ListOrdersByNextTokenRequest();
    listOrdersRequest.setMWSAuthToken(info.getMwsAuthToken());
    listOrdersRequest.setSellerId(info.getSellerId());
    listOrdersRequest.setNextToken(nextToken);

    ListOrdersByNextTokenResponse listOrdersByNextTokenResponse = client.listOrdersByNextToken(listOrdersRequest);
    List<Order> nextOrders = listOrdersByNextTokenResponse.getListOrdersByNextTokenResult().getOrders();
    return nextOrders;
  }

}
