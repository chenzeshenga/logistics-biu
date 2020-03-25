package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.service.impl.AmazonOrderServiceImpl;
import com.abc.vo.Json;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

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
}
