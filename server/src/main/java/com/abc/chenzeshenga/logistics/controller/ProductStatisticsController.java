package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.model.user.CustSysRole;
import com.abc.chenzeshenga.logistics.model.user.CustSysUser;
import com.abc.chenzeshenga.logistics.model.user.UserRoleMapping;
import com.abc.chenzeshenga.logistics.service.ProductStatisticsService;
import com.abc.chenzeshenga.logistics.service.statistics.ProductInWarehouseService;
import com.abc.chenzeshenga.logistics.service.user.RoleService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.service.user.UserRoleService;
import com.abc.chenzeshenga.logistics.service.user.UserService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
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

  @Resource
  private ProductStatisticsMapper productStatisticsMapper;

  private ProductStatisticsService productStatisticsService;

  private ProductInWarehouseService productInWarehouseService;

  private UserService userService;

  private UserRoleService userRoleService;

  private RoleService roleService;

  private UserCommonService userCommonService;

  @Autowired
  public ProductStatisticsController(ProductStatisticsService productStatisticsService, UserService userService,
      UserRoleService userRoleService, RoleService roleService, UserCommonService userCommonService,
      ProductInWarehouseService productInWarehouseService) {
    this.productStatisticsService = productStatisticsService;
    this.userService = userService;
    this.userRoleService = userRoleService;
    this.roleService = roleService;
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
  public Json searchProductInWarehouse(@RequestBody String body) {
    JSONObject jsonObject = JSON.parseObject(body);
    String sku = jsonObject.getString("sku");
    String name = jsonObject.getString("name");
    String owner = jsonObject.getString("owner");
    Page page = PageUtils.getPageParam(jsonObject);
    String username = UserUtils.getUserName();
    boolean isManager = userCommonService.isManagerRole(username);
    if (isManager) {
      
    } else {

    }
    return Json.succ();
  }
}
