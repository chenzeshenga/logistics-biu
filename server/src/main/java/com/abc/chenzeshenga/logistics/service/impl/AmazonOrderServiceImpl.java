package com.abc.chenzeshenga.logistics.service.impl;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.amazon.AmazonStoreInfoMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.amazon.AmazonStoreInfo;
import com.abc.chenzeshenga.logistics.model.dev.AmazonDevInfo;
import com.abc.chenzeshenga.logistics.model.user.UserAmazonInfo;
import com.abc.chenzeshenga.logistics.service.AmazonOrderService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.amazonservices.mws.client.MwsEndpoints;
import com.amazonservices.mws.client.MwsUtl;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders._2013_09_01.MarketplaceWebServiceOrdersException;
import com.amazonservices.mws.orders._2013_09_01.model.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author chenzeshenga
 * @since 2020-02-07 23:30:00
 */
@Service
// @EnableScheduling
@Slf4j
public class AmazonOrderServiceImpl implements AmazonOrderService {

  private static final String CHARACTER_ENCODING = "UTF-8";

  private static final String ALGORITHM = "HmacSHA256";

  private static final String SECRET_KEY = "Hlk378HmiTqB6qNbpX9hcK/V3wE6n8uDwa6uXGq9";

  private static final String AWS_ACCESS_KEY_ID = "AKIAIBNSITOXC4E6G4SQ";

  @Resource private AmazonStoreInfoMapper amazonStoreInfoMapper;

  private RestTemplate restTemplate;

  private OrderMapper orderMapper;

  @Autowired
  public AmazonOrderServiceImpl(RestTemplate restTemplate, OrderMapper orderMapper) {
    this.restTemplate = restTemplate;
    this.orderMapper = orderMapper;
  }

  @Override
  public void syncOrders(String createAfterStr, String createBeforeStr)
      throws MarketplaceWebServiceOrdersException {
    return;
  }

  //  @PostConstruct
  public void syncOrdersByUserId()
      throws NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException,
          URISyntaxException, DocumentException {
    String username = UserUtils.getUserName();
    AmazonStoreInfo amazonStoreInfo = amazonStoreInfoMapper.getAmazonStoreInfoByUserId(username);
    if (amazonStoreInfo != null) {
      Map<String, String> requestParam = generateRequestParam(amazonStoreInfo);
      String url = "https://mws.amazonservices.jp/Orders/2013-09-01?";
      for (Map.Entry<String, String> entry : requestParam.entrySet()) {
        String k = entry.getKey();
        String v = entry.getValue();
        url = (url + "&" + k + "=" + v);
      }
      url = url.replaceFirst("&", "");
      ResponseEntity<String> object =
          restTemplate.exchange(new URI(url), HttpMethod.POST, null, String.class);
      String xml = object.getBody();
      // 1.创建Reader对象
      SAXReader reader = new SAXReader();
      // 2.加载xml
      Document document = reader.read(new ByteArrayInputStream(xml.getBytes()));
      // 3.获取根节点
      Element rootElement = document.getRootElement();
      // 子节点
      List<Element> childElements = rootElement.elements();
      Map<String, Object> mapEle = new HashMap<>();
      // 遍历子节点
      mapEle = getAllElements(childElements, mapEle);
      System.out.println(mapEle);
      ManualOrder manualOrder = new ManualOrder();
      manualOrder.setOrderNo(SnowflakeIdWorker.generateStrId());
      manualOrder.setUserCustomOrderNo((String) mapEle.get("AmazonOrderId"));
      manualOrder.setToZipCode(String.valueOf(mapEle.get("PostalCode")));
      manualOrder.setCategory("1");
      manualOrder.setStatus("1");
      orderMapper.add(manualOrder);
    }
  }

  private Map<String, Object> getAllElements(
      List<Element> childElements, Map<String, Object> mapEle) {
    for (Element ele : childElements) {
      mapEle.put(ele.getName(), ele.getText());
      if (ele.elements().size() > 0) {
        mapEle = getAllElements(ele.elements(), mapEle);
      }
    }
    return mapEle;
  }

  private Map<String, String> generateRequestParam(AmazonStoreInfo amazonStoreInfo)
      throws SignatureException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException,
          UnsupportedEncodingException {
    String secretKey = "Hlk378HmiTqB6qNbpX9hcK/V3wE6n8uDwa6uXGq9";
    // Use the endpoint for your marketplace
    String serviceUrl = MwsEndpoints.JP_PROD.toString();
    // Create set of parameters needed and store in a map
    Map<String, String> parameters = new HashMap<>();
    parameters.put("AWSAccessKeyId", urlEncode("AKIAIBNSITOXC4E6G4SQ"));
    parameters.put("Action", urlEncode("ListOrders"));
    parameters.put("MWSAuthToken", urlEncode(amazonStoreInfo.getMwsAuthToken()));
    parameters.put("SignatureVersion", urlEncode("2"));
    parameters.put("Timestamp", urlEncode(DateUtil.generateDatePattern4Amazon(new Date())));
    parameters.put("Version", urlEncode("2013-09-01"));
    parameters.put("SignatureMethod", urlEncode(ALGORITHM));
    parameters.put("SellerId", urlEncode(amazonStoreInfo.getSellerId()));
    parameters.put("MarketplaceId.Id.1", urlEncode(amazonStoreInfo.getMarketplaceId()));
    parameters.put("CreatedAfter", urlEncode("2020-02-29T16:00:00Z"));
    parameters.put("CreatedBefore", urlEncode("2020-03-20T16:00:00Z"));
    String formattedParameters = calculateStringToSignV2(parameters, serviceUrl);
    String signature = sign(formattedParameters, secretKey);
    parameters.put("Signature", urlEncode(signature));
    return parameters;
  }

  @Override
  //  @PostConstruct
  public void syncOrdersAuto() throws MarketplaceWebServiceOrdersException {
    //    MarketplaceWebServiceOrdersClient marketplaceWebServiceOrdersClient=new
    // MarketplaceWebServiceOrdersClient()

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

  private String getSignature()
      throws SignatureException, URISyntaxException, NoSuchAlgorithmException, InvalidKeyException,
          UnsupportedEncodingException {
    // Use the endpoint for your marketplace
    String serviceUrl = MwsEndpoints.JP_PROD.toString();
    // Create set of parameters needed and store in a map
    HashMap<String, String> parameters = new HashMap<>(12);
    // Add required parameters. Change these as needed.
    parameters.put("AWSAccessKeyId", urlEncode("AKIAIBNSITOXC4E6G4SQ"));
    parameters.put("Action", urlEncode("ListOrders"));
    parameters.put("MWSAuthToken", urlEncode("amzn.mws.46b82a85-7012-ba93-cf4f-6c084bbf1262"));
    parameters.put("SignatureVersion", urlEncode("2"));
    parameters.put("Timestamp", urlEncode(new Date().toString()));
    parameters.put("Version", urlEncode("2013-09-01"));
    parameters.put("Signature", urlEncode(ALGORITHM));
    parameters.put("SignatureMethod", urlEncode(ALGORITHM));
    parameters.put("SellerId", urlEncode("A2SNP3C6EOJ094"));
    parameters.put("CreatedAfter", urlEncode("2020-02-29T16%3A00%3A00Z"));
    parameters.put("CreatedBefore", urlEncode("2020-03-09T16%3A00%3A00Z"));
    parameters.put("MarketplaceId.Id.1", urlEncode("A1VC38T7YXB528"));
    //   &CreatedAfter=2020-02-29T16%3A00%3A00Z
    //      &CreatedBefore=2020-03-09T16%3A00%3A00Z
    //      &MarketplaceId.Id.1=A1VC38T7YXB528

    // Format the parameters as they will appear in final format
    // (without the signature parameter)
    String formattedParameters = calculateStringToSignV2(parameters, serviceUrl);
    String signature = sign(formattedParameters, "test");

    // Add signature to the parameters and display final results
    parameters.put("Signature", urlEncode(signature));
    return "";
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

  /* If Signature Version is 2, string to sign is based on following:
   *
   *    1. The HTTP Request Method followed by an ASCII newline (%0A)
   *
   *    2. The HTTP Host header in the form of lowercase host,
   *       followed by an ASCII newline.
   *
   *    3. The URL encoded HTTP absolute path component of the URI
   *       (up to but not including the query string parameters);
   *       if this is empty use a forward '/'. This parameter is followed
   *       by an ASCII newline.
   *
   *    4. The concatenation of all query string components (names and
   *       values) as UTF-8 characters which are URL encoded as per RFC
   *       3986 (hex characters MUST be uppercase), sorted using
   *       lexicographic byte ordering. Parameter names are separated from
   *       their values by the '=' character (ASCII character 61), even if
   *       the value is empty. Pairs of parameter and values are separated
   *       by the '&' character (ASCII code 38).
   *
   */
  private static String calculateStringToSignV2(Map<String, String> parameters, String serviceUrl)
      throws SignatureException, URISyntaxException {
    // Sort the parameters alphabetically by storing
    // in TreeMap structure
    Map<String, String> sorted = new TreeMap<String, String>();
    sorted.putAll(parameters);

    // Set endpoint value
    URI endpoint = new URI(serviceUrl.toLowerCase());

    // Create flattened (String) representation
    StringBuilder data = new StringBuilder();
    data.append("POST\n");
    data.append(endpoint.getHost());
    data.append("\n");
    data.append("/Orders/2013-09-01\n");

    Iterator<Map.Entry<String, String>> pairs = sorted.entrySet().iterator();
    while (pairs.hasNext()) {
      Map.Entry<String, String> pair = pairs.next();
      if (pair.getValue() != null) {
        data.append(pair.getKey() + "=" + pair.getValue());
      } else {
        data.append(pair.getKey() + "=");
      }

      // Delimit parameters with ampersand (&)
      if (pairs.hasNext()) {
        data.append("&");
      }
    }

    return data.toString();
  }

  /*
   * Sign the text with the given secret key and convert to base64
   */
  private static String sign(String data, String secretKey)
      throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException,
          UnsupportedEncodingException {
    Mac mac = Mac.getInstance(ALGORITHM);
    mac.init(new SecretKeySpec(secretKey.getBytes(CHARACTER_ENCODING), ALGORITHM));
    byte[] signature = mac.doFinal(data.getBytes(CHARACTER_ENCODING));
    String signatureBase64 = new String(Base64.encodeBase64(signature), CHARACTER_ENCODING);
    return new String(signatureBase64);
  }

  private static String urlEncode(String rawValue) {
    String value = (rawValue == null) ? "" : rawValue;
    String encoded = null;

    try {
      encoded =
          URLEncoder.encode(value, CHARACTER_ENCODING)
              .replace("+", "%20")
              .replace("*", "%2A")
              .replace("%7E", "~");
    } catch (UnsupportedEncodingException e) {
      System.err.println("Unknown encoding: " + CHARACTER_ENCODING);
      e.printStackTrace();
    }

    return encoded;
  }

  public static void main(String[] args)
      throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException,
          SignatureException, URISyntaxException {
    // Change this secret key to yours
    String secretKey = "Hlk378HmiTqB6qNbpX9hcK/V3wE6n8uDwa6uXGq9";
    // Use the endpoint for your marketplace
    String serviceUrl = "https://mws.amazonservices.jp";
    // Create set of parameters needed and store in a map
    HashMap<String, String> parameters = new HashMap<>();
    // Add required parameters. Change these as needed.
    parameters.put("AWSAccessKeyId", urlEncode("AKIAIBNSITOXC4E6G4SQ"));
    parameters.put("Action", urlEncode("ListOrders"));
    parameters.put("MWSAuthToken", urlEncode("amzn.mws.46b82a85-7012-ba93-cf4f-6c084bbf1262"));
    parameters.put("SignatureVersion", urlEncode("2"));
    parameters.put("Timestamp", "2020-03-21T14%3A43%3A39Z");
    parameters.put("Version", urlEncode("2013-09-01"));
    parameters.put("SignatureMethod", urlEncode(ALGORITHM));
    parameters.put("SellerId", urlEncode("A2SNP3C6EOJ094"));
    parameters.put("CreatedAfter", "2020-02-29T16%3A00%3A00Z");
    parameters.put("CreatedBefore", "2020-03-09T16%3A00%3A00Z");
    parameters.put("MarketplaceId.Id.1", urlEncode("A1VC38T7YXB528"));
    //   &CreatedAfter=2020-02-29T16%3A00%3A00Z
    //      &CreatedBefore=2020-03-09T16%3A00%3A00Z
    //      &MarketplaceId.Id.1=A1VC38T7YXB528

    // Format the parameters as they will appear in final format
    // (without the signature parameter)
    String formattedParameters = calculateStringToSignV2(parameters, serviceUrl);
    String signature = sign(formattedParameters, secretKey);

    // Add signature to the parameters and display final results
    parameters.put("Signature", urlEncode(signature));
    System.out.println(urlEncode(signature));

    System.out.println(new Date().toGMTString());
  }
}
