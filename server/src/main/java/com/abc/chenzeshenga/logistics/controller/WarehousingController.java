package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.WarehousingContentMapper;
import com.abc.chenzeshenga.logistics.mapper.WarehousingMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.Warehousing;
import com.abc.chenzeshenga.logistics.model.WarehousingContent;
import com.abc.chenzeshenga.logistics.service.WarehousingService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenzesheng
 * @version 1.0
 */
@Slf4j @RestController @RequestMapping("/warehousing") public class WarehousingController {

    @Resource private WarehousingMapper warehousingMapper;

    @Resource private WarehousingContentMapper warehousingContentMapper;

    private WarehousingService warehousingService;

    @Autowired public WarehousingController(WarehousingService warehousingService) {
        this.warehousingService = warehousingService;
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody Warehousing warehousing) {
        String username = UserUtils.getUserName();
        warehousing.setCreator(username);
        warehousing.setUpdator(username);
        Date curr = new Date();
        warehousing.setCreateOn(curr);
        warehousing.setUpdateOn(curr);
        List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
        warehousingMapper.insert(warehousing);
        warehousingContentMapper.insertList(warehousingContentList);
        return Json.succ();
    }

//    @PostMapping @RequestMapping("/list/{type}/{status}")
//    public Json list(@RequestBody String body, @PathVariable String type, @PathVariable String status) {
//        String cname = UserUtils.getUserName();
//        JSONObject jsonObject = JSON.parseObject(body);
//        Page page = PageUtils.getPageParam(jsonObject);
//        Page<ManualOrder> manualOrderPage = orderService.list(page, cname, type, status);
//        enrichOrd(manualOrderPage);
//        return Json.succ().data("page", manualOrderPage);
//    }

}
