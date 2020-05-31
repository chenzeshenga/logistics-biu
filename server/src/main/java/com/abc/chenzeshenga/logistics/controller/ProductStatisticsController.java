package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductMapper;
import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductOutWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.model.common.Pagination;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductInWarehouseSummary;
import com.abc.chenzeshenga.logistics.service.ProductStatisticsService;
import com.abc.chenzeshenga.logistics.service.statistics.ProductInWarehouseService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
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

  @Resource private ProductMapper productMapper;

  @Resource private ProductOutWarehouseMapper productOutWarehouseMapper;

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
  public Json searchProductInWarehouse(
      @RequestBody
          PageQueryEntity<ProductInWarehouseSummary> productInWarehouseSummaryPageQueryEntity) {
    Pagination pagination = productInWarehouseSummaryPageQueryEntity.getPagination();
    long current = pagination.getCurrent();
    int size = pagination.getSize();
    long fromIdx = (current - 1) * size;
    long toIdx = current * size;
    ProductInWarehouseSummary productInWarehouseSummaryReq =
        productInWarehouseSummaryPageQueryEntity.getEntity();
    String owner = productInWarehouseSummaryReq.getOwner();
    String username = UserUtils.getUserName();
    boolean isManager = userCommonService.isManagerRole(username);
    List<ProductInWarehouseSummary> productInWarehouseSummaries;
    long total;
    if (isManager) {
      if (StringUtils.isEmpty(owner)) {
        productInWarehouseSummaries =
            productInWarehouseService.fetchProductInWarehouseWithManagerRole(
                productInWarehouseSummaryReq.getDySku(),
                productInWarehouseSummaryReq.getName(),
                owner,
                fromIdx,
                toIdx);
        total =
            productInWarehouseService.countProductInWarehouseWithManagerRole(
                productInWarehouseSummaryReq.getDySku(),
                productInWarehouseSummaryReq.getName(),
                owner);
      } else {
        productInWarehouseSummaries =
            productInWarehouseService.fetchProductInWarehouseWithUserRole(
                productInWarehouseSummaryReq.getDySku(),
                productInWarehouseSummaryReq.getName(),
                owner,
                fromIdx,
                toIdx);
        total =
            productInWarehouseService.countProductInWarehouseWithUserRole(
                productInWarehouseSummaryReq.getDySku(),
                productInWarehouseSummaryReq.getName(),
                owner);
      }
    } else {
      productInWarehouseSummaries =
          productInWarehouseService.fetchProductInWarehouseWithUserRole(
              productInWarehouseSummaryReq.getDySku(),
              productInWarehouseSummaryReq.getName(),
              username,
              fromIdx,
              toIdx);
      total =
          productInWarehouseService.countProductInWarehouseWithUserRole(
              productInWarehouseSummaryReq.getDySku(),
              productInWarehouseSummaryReq.getName(),
              username);
    }
    pagination.setTotal(total);
    return Json.succ().data("data", productInWarehouseSummaries).data("page", pagination);
  }

  //  @PostMapping("/productOutWarehouse")
  //  @SuppressWarnings("rawtypes")
  //  public Json searchProductOutWarehouse(@RequestBody String body) {
  //    JSONObject jsonObject = JSON.parseObject(body);
  //    String sku = jsonObject.getString("sku");
  //    String name = jsonObject.getString("name");
  //    String owner = jsonObject.getString("owner");
  //    Page page = PageUtils.getPageParam(jsonObject);
  //    String username = UserUtils.getUserName();
  //    boolean isManager = userCommonService.isManagerRole(username);
  //    List<ProductInWarehouseSummary> productInWarehouseSummaries;
  //    if (isManager) {
  //      if (StringUtils.isEmpty(owner)) {
  //        productInWarehouseSummaries =
  //            productInWarehouseService.fetchProductInWarehouseWithManagerRole(
  //                page, sku, name, owner);
  //      } else {
  //        productInWarehouseSummaries =
  //            productInWarehouseService.fetchProductInWarehouseWithUserRole(page, sku, name,
  // owner);
  //      }
  //    } else {
  //      productInWarehouseSummaries =
  //          productInWarehouseService.fetchProductInWarehouseWithUserRole(page, sku, name,
  // username);
  //    }
  //    productInWarehouseSummaries.forEach(
  //        productInWarehouseSummary -> {
  //          String subSku = productInWarehouseSummary.getDySku();
  //          String subOwner = productInWarehouseSummary.getOwner();
  //          ProductOutWarehouse productOutWarehouse = new ProductOutWarehouse();
  //          productOutWarehouse.setDySku(subSku);
  //          productOutWarehouse.setOwner(subOwner);
  //          List<ProductOutWarehouse> productOutWarehouseList =
  //              productOutWarehouseMapper.list(productOutWarehouse);
  //          productInWarehouseSummary.setProductOutWarehouseList(productOutWarehouseList);
  //        });
  //    return Json.succ().data("data", productInWarehouseSummaries);
  //  }
}
