package com.abc.chenzeshenga.logistics.service.impl;

import com.abc.chenzeshenga.logistics.model.dev.AmazonDevInfo;
import com.abc.chenzeshenga.logistics.model.user.UserAmazonInfo;
import com.abc.chenzeshenga.logistics.service.AmazonOrderService;
import com.amazonservices.mws.client.MwsUtl;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author chenzeshenga
 * @since 2020-02-07 23:30:00
 */
@Service
// @EnableScheduling
@Slf4j
public class AmazonOrderServiceImpl implements AmazonOrderService {

  @Override
  public void syncOrders(String createAfterStr, String createBeforeStr)
      throws MarketplaceWebServiceOrdersException {
    return;
  }

  @Override
  //  @PostConstruct
  public void syncOrdersAuto() throws MarketplaceWebServiceOrdersException {
    // todo logic impl
    UserAmazonInfo infoReq = new UserAmazonInfo();
    AmazonDevInfo amazonDevInfo = new AmazonDevInfo();
    //    infoReq.setSellerId("A2SNP3C6E0J094");
    //    infoReq.setAwsAccessKeyId("amzn.mws.46b82a85-7012-ba93-cf4f-6c084bbf1262");
    //    infoReq.setSecretKey("Hlk378HmiTqB6qNbpX9hcK/V3wE6n8uDwa6uXGq9");
    //    infoReq.setAwsAccessKey("AKIAIBNSITOXC4E6G4SQ");
    List<UserAmazonInfo> storeInfoReqList = new ArrayList<>();
    for (UserAmazonInfo storeInfoReq : storeInfoReqList) {
      String userId = storeInfoReq.getUserId();
      //      String username = userService.getUsernameByUserId(userId);
      String username = "";
      MarketplaceWebServiceOrdersClient client =
          new MarketplaceWebServiceOrdersClient("", "", "", "");

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
          List<Order> temp = getNextOrderList(client, nextToken, infoReq);
          orders.addAll(temp);
        }
        log.info(
            "list order RequestId --> {}, Timestamp --> {}",
            rhmd.getRequestId(),
            rhmd.getTimestamp());
        log.info("response original msg as \r\n {}", response.toXMLFragment());

        for (Order order : orders) {
          storeSaleRecord(username, infoReq, client, order);
        }
      } catch (MarketplaceWebServiceOrdersException ex) {
        log.error("error stack info ", ex);
      }
    }
    for (UserAmazonInfo storeInfoReq : storeInfoReqList) {
      String userId = storeInfoReq.getUserId();
      //      String username = userService.getUsernameByUserId(userId);
      String username = "";
      MarketplaceWebServiceOrdersClient client =
          new MarketplaceWebServiceOrdersClient("", "", "", "");

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
          List<Order> temp = getNextOrderList(client, nextToken, infoReq);
          orders.addAll(temp);
        }
        log.info(
            "list order RequestId --> {}, Timestamp --> {}",
            rhmd.getRequestId(),
            rhmd.getTimestamp());
        log.info("response original msg as \r\n {}", response.toXMLFragment());

        for (Order order : orders) {
          storeSaleRecord(username, infoReq, client, order);
        }
      } catch (MarketplaceWebServiceOrdersException ex) {
        log.error("error stack info ", ex);
      }
    }
  }

  private void storeSaleRecord(
      String username,
      UserAmazonInfo storeInfoReq,
      MarketplaceWebServiceOrdersClient client,
      Order order) {
    ListOrderItemsRequest listOrderItemsRequest = new ListOrderItemsRequest();
    listOrderItemsRequest.setSellerId(storeInfoReq.getSellerId());
    listOrderItemsRequest.setMWSAuthToken(storeInfoReq.getMwsAuthToken());
    listOrderItemsRequest.setAmazonOrderId(order.getAmazonOrderId());

    ListOrderItemsResponse listOrderItemsResponse = client.listOrderItems(listOrderItemsRequest);
    ResponseHeaderMetadata responseHeaderMetadata =
        listOrderItemsResponse.getResponseHeaderMetadata();

    log.info(
        "list order item RequestId --> {}, Timestamp --> {}, AmazonOrderId --> {}",
        responseHeaderMetadata.getRequestId(),
        responseHeaderMetadata.getTimestamp(),
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
    //      saleRecord.setToContact(StringUtils.isEmpty(address.getPhone()) ? "" :
    // address.getPhone());
    //      saleRecord.setToPostCode(StringUtils.isEmpty(address.getPostalCode()) ? "" :
    // address.getPostalCode());
    //      String stateOrRegion = StringUtils.isEmpty(address.getStateOrRegion()) ? "" :
    // address.getStateOrRegion();
    //      String addressLine1 = StringUtils.isEmpty(address.getAddressLine1()) ? "" :
    // address.getAddressLine1();
    //      saleRecord.setToWhere(stateOrRegion + "--" + addressLine1);
    //      saleRecord.setToWho(address.getName());
    //    }
    //    Money money = order.getOrderTotal();
    //    if (money != null) {
    //      saleRecord.setAmountOnbehalf(order.getOrderTotal().getCurrencyCode() + "--" +
    // order.getOrderTotal().getAmount());
    //    }
    //    saleRecord.setName(orderItem.getTitle());
    //    saleRecord.setNum(String.valueOf(order.getNumberOfItemsUnshipped()));
    //    saleRecord.setCreator(username);
    //    saleRecord.setCreateOn(new Date());
    //    saleRecord.setStatus(SeqConstant.ONE);
    //    saleService.insert(saleRecord);
    //    log.info("order {} was synchronized", saleRecord);
  }

  //  private void syncOrderManual(AtomicReference<ListOrdersResponse> response, String username,
  // String createAfterStr,
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
  //      log.info("list order RequestId --> {}, Timestamp --> {}", rhmd.getRequestId(),
  // rhmd.getTimestamp());
  //      log.info("response original msg as \r\n {}", response.get().toXMLFragment());
  //
  //      for (Order order : orders) {
  //        storeSaleRecord(username, info, client, order);
  //      }
  //    } catch (MarketplaceWebServiceOrdersException ex) {
  //      log.error(ex.getMessage());
  //    }
  //  }

  private static List<Order> getNextOrderList(
      MarketplaceWebServiceOrdersClient client, String nextToken, UserAmazonInfo info) {
    ListOrdersByNextTokenRequest listOrdersRequest = new ListOrdersByNextTokenRequest();
    listOrdersRequest.setMWSAuthToken(info.getMwsAuthToken());
    listOrdersRequest.setSellerId(info.getSellerId());
    listOrdersRequest.setNextToken(nextToken);

    ListOrdersByNextTokenResponse listOrdersByNextTokenResponse =
        client.listOrdersByNextToken(listOrdersRequest);
    List<Order> nextOrders =
        listOrdersByNextTokenResponse.getListOrdersByNextTokenResult().getOrders();
    return nextOrders;
  }
}
