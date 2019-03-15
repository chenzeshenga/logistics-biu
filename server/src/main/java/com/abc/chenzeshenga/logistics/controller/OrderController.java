package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.ChannelCache;
import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j @RestController @RequestMapping("/ord") public class OrderController {

    @Resource private OrderMapper orderMapper;

    @Autowired private OrderService orderService;

    @Autowired private JapanAddressCache japanAddressCache;

    @Autowired private LabelCache labelCache;

    @Autowired private ChannelCache channelCache;

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody ManualOrder manualOrder) {
        setAddress(manualOrder);
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
            manualOrderContents.forEach(manualOrderContent -> manualOrderContent.setOrdno(manualOrder.getOrderNo()));
            orderMapper.insertContent(manualOrderContents);
        }
        return Json.succ().data(result);
    }

    @PostMapping @RequestMapping("/update") public Json update(@RequestBody ManualOrder manualOrder) {
        log.info(manualOrder.toString());
        setAddress(manualOrder);
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        int result = orderMapper.update(manualOrder);
        List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
        if (manualOrderContents != null && !manualOrderContents.isEmpty()) {
            orderMapper.deleteContent(manualOrder.getOrderNo());
            manualOrderContents.forEach(manualOrderContent -> manualOrderContent.setOrdno(manualOrder.getOrderNo()));
            orderMapper.insertContent(manualOrderContents);
        }
        return Json.succ().data(result);
    }

    private void setAddress(@RequestBody ManualOrder manualOrder) {
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
            manualOrder.setFromAddressDesc(from.getKenName() + from.getCityName() + from.getTownName() + manualOrder.getFromDetailAddress());
            JpDetailAddress to = japanAddressCache
                .getJpDetailAddress(Integer.valueOf(manualOrder.getToKenId()), Integer.valueOf(manualOrder.getToCityId()),
                    Integer.valueOf(manualOrder.getToTownId()));
            manualOrder.setToKenName(to.getKenName());
            manualOrder.setToCityName(to.getCityName());
            manualOrder.setToTownName(to.getTownName());
            manualOrder.setToAddressDesc(to.getKenName() + to.getCityName() + to.getTownName() + manualOrder.getToDetailAddress());
            manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
            manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
            manualOrder.setChannelDesc(channelCache.channelLabel(manualOrder.getChannel()));
        });
        return Json.succ().data("page", manualOrderPage);
    }

    @GetMapping @RequestMapping("/delete/{ordNo}") public Json delete(@PathVariable String ordNo) {
        orderMapper.delete(ordNo);
        orderMapper.deleteContent(ordNo);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/get/{ordNo}") public Json selectByPk(@PathVariable String ordNo) {
        ManualOrder manualOrder = orderMapper.selectById(ordNo);
        List<ManualOrderContent> manualOrderContents = orderMapper.listContent(ordNo);
        manualOrder.setManualOrderContents(manualOrderContents);
        List<String> selectedAddress = new ArrayList<>();
        selectedAddress.add(manualOrder.getFromKenId());
        selectedAddress.add(manualOrder.getFromCityId());
        selectedAddress.add(manualOrder.getFromTownId());
        manualOrder.setSelectedAddress(selectedAddress);
        Map<String, String> address = manualOrder.getAddress();
        if (address == null || address.isEmpty()) {
            address = new HashMap<>();
        }
        address.put("ken", manualOrder.getFromKenId());
        address.put("city", manualOrder.getFromCityId());
        address.put("town", manualOrder.getFromTownId());
        List<String> selectedToAddress = new ArrayList<>();
        selectedToAddress.add(manualOrder.getToKenId());
        selectedToAddress.add(manualOrder.getToCityId());
        selectedToAddress.add(manualOrder.getToTownId());
        manualOrder.setSelectedToAddress(selectedToAddress);
        Map<String, String> toAddress = manualOrder.getToAddress();
        if (toAddress == null || toAddress.isEmpty()) {
            toAddress = new HashMap<>();
        }
        toAddress.put("ken", manualOrder.getToKenId());
        toAddress.put("city", manualOrder.getToCityId());
        toAddress.put("town", manualOrder.getToTownId());
        return Json.succ().data(manualOrder);

    }

    @GetMapping @RequestMapping("/pickup/{regTxt}") public Json search(@PathVariable String regTxt) {
        List<ManualOrderContent> contentList = orderMapper.listContent(regTxt);
        return Json.succ().data(contentList);
    }

    @PostMapping @RequestMapping("/pickup") public Json pickup(@RequestBody List<ManualOrderContent> manualOrderContentList) {
        log.info(manualOrderContentList.toString());
        String ordno;
        if (!manualOrderContentList.isEmpty()) {
            ordno = manualOrderContentList.get(0).getOrdno();
            orderMapper.deleteContent(ordno);
            orderMapper.insertContent(manualOrderContentList);
        } else {
            return Json.fail();
        }
        return Json.succ();
    }

    @GetMapping @RequestMapping("/update/{ordno}/{status}") public Json statusUpdate(@PathVariable String ordno, @PathVariable String status) {
        orderMapper.statusUpdate(ordno, status);
        return Json.succ();
    }

}
