package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ProductStatisticsMapper;
import com.abc.chenzeshenga.logistics.model.ProductStatistics;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController @RequestMapping("/statistics") public class ProductStatisticsController {

    @Resource private ProductStatisticsMapper productStatisticsMapper;

    @GetMapping @RequestMapping("/list") public Json list() {
        String username = UserUtils.getUserName();
        List<ProductStatistics> productStatisticsList = new ArrayList<>();
        if ("admin".equals(username)) {
            productStatisticsList = productStatisticsMapper.selectAll();
        } else {
            productStatisticsList = productStatisticsMapper.selectAllByUsername(username);
        }
        return Json.succ().data(productStatisticsList);
    }

}
