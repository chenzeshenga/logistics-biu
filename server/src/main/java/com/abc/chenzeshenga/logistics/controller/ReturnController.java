package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Product;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.07.19
 */
@RestController @Slf4j @RequestMapping("/return") public class ReturnController {

    @Resource private ReturnMapper returnMapper;

    private CommonController commonController;

    @Autowired public ReturnController(CommonController commonController) {
        this.commonController = commonController;
    }

    @PostMapping(value = "/img/put") public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile,
        @RequestParam(value = "returnNo") String returnNo) throws IOException {
        if (StringUtils.isEmpty(returnNo)) {
            throw new IllegalArgumentException("退货单号必填");
        }
        Return returnOrder = returnMapper.selectByPk(returnNo);
        if (returnOrder == null) {
            returnOrder = new Return();
            returnOrder.setReturnNo(returnNo);
        }
        String uuid = UUID.randomUUID().toString();
        commonController.putImg(multipartFile, uuid);
        returnOrder.setImgs(uuid);
        returnOrder.setUpdateOn(new Date());
        returnOrder.setUpdator(UserUtils.getUserName());
        returnMapper.insertReturnOrder(returnOrder);
        return Json.succ();
    }

    @GetMapping(value = "/img/drop") public Json dropImg(@RequestParam(value = "returnNo") String returnNo) {
        if (StringUtils.isEmpty(returnNo)) {
            throw new IllegalArgumentException("退货单号必填");
        }
        Return returnOrder = returnMapper.selectByPk(returnNo);
        returnOrder.setImgs("");
        returnMapper.updateByPk(returnOrder);
        return Json.succ();
    }

    @PostMapping("/add") public Json addReturnOrder(@RequestBody Return returnOrder) {
        String returnNo = returnOrder.getReturnNo();
        Return ori = returnMapper.selectByPk(returnNo);
        if (ori == null) {
            returnOrder.setStatus("新建");
            returnOrder.setUpdateOn(new Date());
            returnOrder.setCreateOn(new Date());
            returnOrder.setUpdator(UserUtils.getUserName());
            returnMapper.insertReturnOrder(returnOrder);
            List<ReturnContent> returnContentList = returnOrder.getContentList();
            if (returnContentList != null && !returnContentList.isEmpty()) {
                returnMapper.insertContent(returnContentList);
            }
        } else {
            ori.setWithoutOrderNoFlag(returnOrder.isWithoutOrderNoFlag());
            ori.setToName(returnOrder.getToName());
            ori.setToContact(returnOrder.getToContact());
            ori.setToZipCode(returnOrder.getToZipCode());
            ori.setToDetailAddress(returnOrder.getToDetailAddress());
            ori.setToKenId(returnOrder.getToKenId());
            ori.setToCityId(returnOrder.getToCityId());
            ori.setToTownId(returnOrder.getToTownId());
            ori.setFromName(returnOrder.getFromName());
            ori.setFromContact(returnOrder.getFromContact());
            ori.setFromZipCode(returnOrder.getFromZipCode());
            ori.setFromDetailAddress(returnOrder.getFromDetailAddress());
            ori.setFromKenId(returnOrder.getFromKenId());
            ori.setFromCityId(returnOrder.getFromCityId());
            ori.setFromTownId(returnOrder.getFromTownId());
            ori.setCarrier(returnOrder.getCarrier());
            ori.setTrackNo(returnOrder.getTrackNo());
            ori.setUpdator(UserUtils.getUserName());
            ori.setUpdateOn(new Date());
            ori.setStatus("新建");
            ori.setOrderNo(returnOrder.getOrderNo());
            returnMapper.updateByPk(returnOrder);
            List<ReturnContent> returnContentList = returnOrder.getContentList();
            if (returnContentList != null && !returnContentList.isEmpty()) {
                returnMapper.insertContent(returnContentList);
            }
        }
        return Json.succ();
    }

    @PostMapping("/update") public Json updateReturnOrder(@RequestBody Return returnOrder) {
        String returnNo = returnOrder.getReturnNo();
        Return ori = returnMapper.selectByPk(returnNo);
        if (ori == null) {
            returnOrder.setUpdateOn(new Date());
            returnOrder.setCreateOn(new Date());
            returnOrder.setUpdator(UserUtils.getUserName());
            returnMapper.insertReturnOrder(returnOrder);
            List<ReturnContent> returnContentList = returnOrder.getContentList();
            if (returnContentList != null && !returnContentList.isEmpty()) {
                returnMapper.insertContent(returnContentList);
            }
        } else {
            ori.setWithoutOrderNoFlag(returnOrder.isWithoutOrderNoFlag());
            ori.setToName(returnOrder.getToName());
            ori.setToContact(returnOrder.getToContact());
            ori.setToZipCode(returnOrder.getToZipCode());
            ori.setToDetailAddress(returnOrder.getToDetailAddress());
            ori.setToKenId(returnOrder.getToKenId());
            ori.setToCityId(returnOrder.getToCityId());
            ori.setToTownId(returnOrder.getToTownId());
            ori.setFromName(returnOrder.getFromName());
            ori.setFromContact(returnOrder.getFromContact());
            ori.setFromZipCode(returnOrder.getFromZipCode());
            ori.setFromDetailAddress(returnOrder.getFromDetailAddress());
            ori.setFromKenId(returnOrder.getFromKenId());
            ori.setFromCityId(returnOrder.getFromCityId());
            ori.setFromTownId(returnOrder.getFromTownId());
            ori.setCarrier(returnOrder.getCarrier());
            ori.setTrackNo(returnOrder.getTrackNo());
            ori.setUpdator(UserUtils.getUserName());
            ori.setUpdateOn(new Date());
            ori.setStatus(returnOrder.getStatus());
            ori.setOrderNo(returnOrder.getOrderNo());
            returnMapper.updateByPk(returnOrder);
            List<ReturnContent> returnContentList = returnOrder.getContentList();
            if (returnContentList != null && !returnContentList.isEmpty()) {
                returnMapper.insertContent(returnContentList);
            }
        }
        return Json.succ();
    }

}
