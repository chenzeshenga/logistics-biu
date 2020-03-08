package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.JpDetailAddress;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.service.ReturnService;
import com.abc.chenzeshenga.logistics.service.returning.ReturnCommonService;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzesheng
 * @version 1.0
 * @since 2019.07.19
 */
@RestController
@Slf4j
@RequestMapping("/return")
public class ReturnController {

  @Resource private ReturnMapper returnMapper;

  private ReturnService returnService;

  private ReturnCommonService returnCommonService;

  private CommonController commonController;

  private JapanAddressCache japanAddressCache;

  @Autowired
  public ReturnController(
      CommonController commonController,
      ReturnService returnService,
      JapanAddressCache japanAddressCache,
      ReturnCommonService returnCommonService) {
    this.commonController = commonController;
    this.returnService = returnService;
    this.japanAddressCache = japanAddressCache;
    this.returnCommonService = returnCommonService;
  }

  @PostMapping(value = "/img/put")
  public Json putImg(
      @RequestParam(value = "file") MultipartFile multipartFile,
      @RequestParam(value = "returnNo") String returnNo)
      throws IOException {
    if (StringUtils.isEmpty(returnNo)) {
      throw new IllegalArgumentException("退货单号必填");
    }
    Return returnOrder = returnMapper.selectByPk(returnNo);
    if (returnOrder == null) {
      returnOrder = new Return();
      returnOrder.setReturnNo(returnNo);
    }
    String uuid = SnowflakeIdWorker.generateStrId();
    commonController.putImg(multipartFile, uuid);
    String uuid4Imgs = returnOrder.getImgs();
    if (StringUtils.isBlank(uuid4Imgs)) {
      uuid4Imgs = uuid;
    } else {
      uuid4Imgs = uuid4Imgs + ";" + uuid;
    }
    returnOrder.setImgs(uuid4Imgs);
    returnOrder.setUpdateOn(new Date());
    returnOrder.setCreateOn(new Date());
    returnOrder.setUpdator(UserUtils.getUserName());
    returnMapper.insertReturnOrder(returnOrder);
    return Json.succ();
  }

  @GetMapping(value = "/img/drop")
  public Json dropImg(@RequestParam(value = "returnNo") String returnNo) {
    if (StringUtils.isEmpty(returnNo)) {
      throw new IllegalArgumentException("退货单号必填");
    }
    Return returnOrder = returnMapper.selectByPk(returnNo);
    returnOrder.setImgs("");
    returnMapper.updateByPk(returnOrder);
    return Json.succ();
  }

  @PostMapping("/add")
  public Json addReturnOrder(@RequestBody Return returnOrder) {
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
        returnContentList.forEach(
            returnContent -> {
              String returnNoInContent = returnContent.getReturnNo();
              if (StringUtils.isBlank(returnNoInContent)) {
                returnContent.setReturnNo(returnNo);
              }
            });
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
      ori.setCreator(returnOrder.getCreator());
      ori.setStatus("新建");
      ori.setCreateOn(new Date());
      ori.setOrderNo(returnOrder.getOrderNo());
      returnMapper.updateByPk(ori);
      List<ReturnContent> returnContentList = returnOrder.getContentList();
      if (returnContentList != null && !returnContentList.isEmpty()) {
        returnContentList.forEach(
            returnContent -> returnContent.setUuid(SnowflakeIdWorker.generateStrId()));
        returnMapper.insertContent(returnContentList);
      }
    }
    return Json.succ();
  }

  @PostMapping("/update")
  public Json updateReturnOrder(@RequestBody Return returnOrder) {
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

  @PostMapping("/updatePkgInfo")
  public Json updatePkgInfo(@RequestBody Return returnOrd) {
    returnOrd.setStatus("已收货");
    returnService.updatePkgInfo(returnOrd);
    return Json.succ();
  }

  @PostMapping("/list")
  public Json list(
      @RequestBody String body,
      @RequestParam String type,
      @RequestParam String status,
      @RequestParam(required = false, defaultValue = "0") long from,
      @RequestParam(required = false, defaultValue = "0") long to) {
    JSONObject jsonObject = JSON.parseObject(body);
    Page page = PageUtils.getPageParam(jsonObject);
    long curr = System.currentTimeMillis();
    if (from == 0) {
      from = curr - 7 * 24 * 60 * 60 * 1000;
    }
    if (to == 0) {
      to = curr + 7 * 24 * 60 * 60 * 1000;
    }
    Date fromDate = new Date(from);
    Date toDate = new Date(to);
    Page<Return> returnPage;
    if ("withoutUser".equals(type)) {
      returnPage = returnService.list(page, status, fromDate, toDate);
    } else if ("withUser".equals(type)) {
      returnPage = returnService.listAll(page, status, fromDate, toDate);
    } else {
      return Json.succ().data("page", new Page<Return>());
    }
    List<Return> returnList = returnPage.getRecords();
    returnList.forEach(
        returning -> {
          try {
            JpDetailAddress jpDetailAddress =
                japanAddressCache.getJpDetailAddress(
                    Integer.parseInt(returning.getFromKenId()),
                    Integer.parseInt(returning.getFromCityId()),
                    Integer.parseInt(returning.getFromTownId()));
            if (jpDetailAddress != null) {
              returning.setFromDetailAddress(
                  jpDetailAddress.toString() + returning.getFromDetailAddress());
            }
          } catch (Exception e) {
            log.error("error");
          }
          if (StringUtils.isBlank(returning.getToKenId())
              || StringUtils.isBlank(returning.getToCityId())
              || StringUtils.isBlank(returning.getToTownId())) {
            returning.setToDetailAddress("日本岡山仓(okayama)");
            returning.setToName("东岳物流");
          } else {
            try {
              JpDetailAddress fromJpDetailAddress =
                  japanAddressCache.getJpDetailAddress(
                      Integer.parseInt(returning.getToKenId()),
                      Integer.parseInt(returning.getToCityId()),
                      Integer.parseInt(returning.getToTownId()));
              if (fromJpDetailAddress != null) {
                returning.setToDetailAddress(
                    fromJpDetailAddress.toString() + returning.getToDetailAddress());
              }
            } catch (Exception e) {
              log.error("error");
            }
          }
        });
    return Json.succ().data("page", returnPage);
  }

  @PostMapping("/common/list")
  public Json listReturnOrd(
      @RequestBody String req, @RequestParam String type, @RequestParam String status)
      throws IOException {
    com.abc.chenzeshenga.logistics.model.common.Page<Return> returnPage =
        returnCommonService.list(req, type, status);
    List<Return> returnList = returnPage.getData();
    returnList.forEach(
        returning -> {
          try {
            JpDetailAddress jpDetailAddress =
                japanAddressCache.getJpDetailAddress(
                    Integer.parseInt(returning.getFromKenId()),
                    Integer.parseInt(returning.getFromCityId()),
                    Integer.parseInt(returning.getFromTownId()));
            if (jpDetailAddress != null) {
              returning.setFromDetailAddress(
                  jpDetailAddress.toString() + returning.getFromDetailAddress());
            }
          } catch (Exception e) {
            log.error("error");
          }
          if (StringUtils.isBlank(returning.getToKenId())
              || StringUtils.isBlank(returning.getToCityId())
              || StringUtils.isBlank(returning.getToTownId())) {
            returning.setToDetailAddress("日本岡山仓(okayama)");
            returning.setToName("东岳物流");
          } else {
            try {
              JpDetailAddress fromJpDetailAddress =
                  japanAddressCache.getJpDetailAddress(
                      Integer.parseInt(returning.getToKenId()),
                      Integer.parseInt(returning.getToCityId()),
                      Integer.parseInt(returning.getToTownId()));
              if (fromJpDetailAddress != null) {
                returning.setToDetailAddress(
                    fromJpDetailAddress.toString() + returning.getToDetailAddress());
              }
            } catch (Exception e) {
              log.error("error");
            }
          }
        });
    return Json.succ().data("page", returnPage);
  }

  @GetMapping("/returnOrdDetail")
  public Json getReturnOrdDetail(@RequestParam String returnNo) {
    return Json.succ().data("returnOrd", returnService.getReturnOrdDetail(returnNo));
  }
}
