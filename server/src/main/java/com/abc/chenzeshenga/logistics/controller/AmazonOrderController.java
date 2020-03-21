package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.service.impl.AmazonOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public void orderSync() {
    amazonOrderService.syncOrders("2020-02-29T16:00:00Z", "2020-03-09T16:00:00Z");
  }
}
