package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.JpDetailAddress;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.service.OrderService;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/ord") public class OrderController {

    @Resource private OrderMapper orderMapper;

    @Autowired private OrderService orderService;

    @Autowired private JapanAddressCache japanAddressCache;

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody ManualOrder manualOrder) {
        List<String> fromAddress = manualOrder.getSelectedAddress();
        if (fromAddress != null && !fromAddress.isEmpty()) {
            manualOrder.setFromKenId(fromAddress.get(0));
            manualOrder.setFromCityId(fromAddress.get(1));
            manualOrder.setFromTownId(fromAddress.get(2));
        }
        List<String> toAddress = manualOrder.getSelectedToAddress();
        if (toAddress != null && !toAddress.isEmpty()) {
            manualOrder.setToKenId(toAddress.get(0));
            manualOrder.setToCityId(toAddress.get(1));
            manualOrder.setToTownId(toAddress.get(2));
        }
        manualOrder.setStatus("1");
        Date curr = new Date();
        manualOrder.setCreateOn(curr);
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setCreator(cname);
        manualOrder.setUpdator(cname);
        int result = orderMapper.add(manualOrder);
        List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
        if (manualOrderContents != null && !manualOrderContents.isEmpty()) {
            manualOrderContents.forEach(manualOrderContent -> manualOrderContent.setSaleRecNo(manualOrder.getOrderNo()));
            orderMapper.insertContent(manualOrderContents);
        }
        return Json.succ().data(result);
    }

    @PostMapping @RequestMapping("/list/{type}/{status}")
    public Json list(@RequestBody String body, @PathVariable String type, @PathVariable String status) {
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<ManualOrder> manualOrderPage = orderService.list(page, cname, type, status);
        List<ManualOrder> manualOrderList = manualOrderPage.getRecords();
        manualOrderList.forEach(manualOrder -> {
            JpDetailAddress from = japanAddressCache
                .getJpDetailAddress(Integer.valueOf(manualOrder.getFromKenId()), Integer.valueOf(manualOrder.getFromCityId()),
                    Integer.valueOf(manualOrder.getFromTownId()));
            manualOrder.setFromKenName(from.getKenName());
            manualOrder.setFromCityName(from.getCityName());
            manualOrder.setFromTownName(from.getTownName());
            JpDetailAddress to = japanAddressCache
                .getJpDetailAddress(Integer.valueOf(manualOrder.getToKenId()), Integer.valueOf(manualOrder.getToCityId()),
                    Integer.valueOf(manualOrder.getToTownId()));
            manualOrder.setToKenName(to.getKenName());
            manualOrder.setToCityName(to.getCityName());
            manualOrder.setToTownName(to.getTownName());
        });

        return Json.succ().data("page", manualOrderPage);
    }

    @GetMapping @RequestMapping("/delete/{ordNo}") public Json delete(@PathVariable String ordNo) {
        orderMapper.delete(ordNo);
        orderMapper.deleteContent(ordNo);
        return Json.succ();
    }

}
