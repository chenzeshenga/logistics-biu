package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.model.amazon.AmazonStoreInfo;
import com.abc.chenzeshenga.logistics.service.impl.AmazonOrderServiceImpl;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzesheng
 * @since 2020-03-19 22:54
 */
@RestController
@RequestMapping("/amazon")
public class AmazonOrderController {

  private AmazonOrderServiceImpl amazonOrderService;

  @Autowired
  public AmazonOrderController(AmazonOrderServiceImpl amazonOrderService) {
    this.amazonOrderService = amazonOrderService;
  }

  @GetMapping("/sync")
  public Json orderSync()
      throws URISyntaxException, NoSuchAlgorithmException, IOException, SignatureException,
          DocumentException, InvalidKeyException {
    amazonOrderService.syncOrdersByUserId();
    return Json.succ();
  }

  @PostMapping("/storeInfo")
  public Json addStoreInfo(@RequestBody AmazonStoreInfo amazonStoreInfo) {
    String sellerId = amazonStoreInfo.getSellerId();
    if (!StringUtils.isEmpty(sellerId)) {
      AmazonStoreInfo oriAmazonStoreInfo =
          amazonOrderService.getAmazonOrderInfoBySellerId(sellerId);
      if (oriAmazonStoreInfo != null) {
        return Json.fail("addStoreInfo", "sellerId对应店铺已存在，请修改原店铺");
      }
    }
    amazonStoreInfo.setUserId(UserUtils.getUserName());
    amazonStoreInfo.setUuid(SnowflakeIdWorker.generateStrId());
    amazonStoreInfo.setEnable(true);
    amazonOrderService.addStoreInfo(amazonStoreInfo);
    return Json.succ();
  }

  @PostMapping("/list")
  public void list() {}
}
