package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.vo.Json;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController
@RequestMapping("/scan")
@Slf4j
public class ScanController {

  @Value("${logistics.base.url}")
  private String baseUrl;

  @Resource private OrderMapper orderMapper;

  @Resource private ProductMapper productMapper;

  @GetMapping("/ord/content/{ordno}")
  public Json getOrdContent(@PathVariable String ordno) {
    List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
    manualOrderContentList.forEach(
        manualOrderContent -> {
          String skuPk = manualOrderContent.getSku().split("/")[0];
          log.info(skuPk);
          Product product = productMapper.selectByPrimaryKey(skuPk);
          log.info(product.toString());
          manualOrderContent.setImgUrl(baseUrl + "img/" + product.getImg1());
        });
    return Json.succ().data(manualOrderContentList);
  }
}
