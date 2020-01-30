package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.abc.chenzeshenga.logistics.service.ProductStatisticsService;
import com.abc.chenzeshenga.logistics.service.statistics.ProductInWarehouseService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenzeshenga
 * @version 1.0
 * @since 20190809
 */
@RestController
@RequestMapping("/statistics")
public class ProductStatisticsController {

  @Resource private ProductStatisticsMapper productStatisticsMapper;

  @Resource private UpShelfProductMapper upShelfProductMapper;

  private ProductStatisticsService productStatisticsService;

  private ProductInWarehouseService productInWarehouseService;

  private UserCommonService userCommonService;

  @Autowired
  public ProductStatisticsController(
      ProductStatisticsService productStatisticsService,
      UserCommonService userCommonService,
      ProductInWarehouseService productInWarehouseService) {
    this.productStatisticsService = productStatisticsService;
    this.userCommonService = userCommonService;
    this.productInWarehouseService = productInWarehouseService;
  }

  @PostMapping
  @RequestMapping("/list")
  public Json list(@RequestBody String body) {
    String username = UserUtils.getUserName();
    JSONObject jsonObject = JSON.parseObject(body);
    Page page = PageUtils.getPageParam(jsonObject);
    List<ProductStatistics> productStatisticsList;
    Page<ProductStatistics> productStatisticsPage = new Page<>();
    if ("admin".equals(username)) {
      productStatisticsPage = productStatisticsService.selectAll(page);
    } else {
      productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
    }
    return Json.succ().data("page", productStatisticsPage);
  }

  @PostMapping("/listBySearch")
  public Json listBySearch(@RequestBody String body) {
    String username = UserUtils.getUserName();
    JSONObject jsonObject = JSON.parseObject(body);
    String sku = jsonObject.getString("sku");
    String name = jsonObject.getString("name");
    String owner = jsonObject.getString("owner");
    Page page = PageUtils.getPageParam(jsonObject);
    List<ProductStatistics> productStatisticsList;
    Page<ProductStatistics> productStatisticsPage = new Page<>();
    if ("admin".equals(username)) {
      productStatisticsPage = productStatisticsService.selectAllBySearch(page, sku, name, owner);
    } else {
      productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
    }
    return Json.succ().data("page", productStatisticsPage);
  }

  @PostMapping("/productInWarehouse")
  @SuppressWarnings("rawtypes")
  public Json searchProductInWarehouse(@RequestBody String body) {
    JSONObject jsonObject = JSON.parseObject(body);
    String sku = jsonObject.getString("sku");
    String name = jsonObject.getString("name");
    String owner = jsonObject.getString("owner");
    Page page = PageUtils.getPageParam(jsonObject);
    String username = UserUtils.getUserName();
    boolean isManager = userCommonService.isManagerRole(username);
    List<ProductInWarehouseSummary> productInWarehouseSummaries = new ArrayList<>();
    if (isManager) {
      productInWarehouseSummaries =
          productInWarehouseService.fetchProductInWarehouseWithManagerRole(page, sku, name, owner);
    } else {
      productInWarehouseService.fetchProductInWarehouseWithUserRole(page, sku, name, username);
    }
    productInWarehouseSummaries.forEach(
        productInWarehouseSummary -> {
          String subSku = productInWarehouseSummary.getSku();
          String subOwner = productInWarehouseSummary.getOwner();
          Map<String, Object> columnMap = new HashMap<>(2);
          columnMap.put("sku", subSku);
          columnMap.put("owner", subOwner);
          List<UpShelfProduct> upShelfProducts = upShelfProductMapper.selectByMap(columnMap);
          Date curr = new Date();
          upShelfProducts.forEach(
              product -> {
                Date uptime = product.getUptime();
                product.setDatePoor(DateUtil.getDatePoor(curr, uptime));
              });
          productInWarehouseSummary.setChildren(upShelfProducts);
        });

    return Json.succ().data("data", productInWarehouseSummaries);
  }
}
