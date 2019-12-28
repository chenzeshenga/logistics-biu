package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.service.ProductStatisticsService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @Autowired
    public ProductStatisticsController(ProductStatisticsService productStatisticsService) {
        this.productStatisticsService = productStatisticsService;
    }

    @PostMapping
    @RequestMapping("/list")
    public Json list(@RequestBody String body) {
        String username = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
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
        Page<ProductStatistics> productStatisticsPage = new Page<>();
        if ("admin".equals(username)) {
            productStatisticsPage = productStatisticsService.selectAllBySearch(page, sku, name, owner);
        } else {
            productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
        }
        return Json.succ().data("page", productStatisticsPage);
    }

}
