package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.JpDetailAddress;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@RestController @RequestMapping("/ord") public class OrderController {

    @Resource private OrderMapper orderMapper;

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

}
