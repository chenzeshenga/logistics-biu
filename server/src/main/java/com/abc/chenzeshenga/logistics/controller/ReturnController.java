package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.mapper.ReturnMapper;
import com.abc.chenzeshenga.logistics.model.Return;
import com.abc.chenzeshenga.logistics.model.ReturnContent;
import com.abc.chenzeshenga.logistics.model.claim.ClaimContentDealing;
import com.abc.chenzeshenga.logistics.model.claim.ClaimPackage;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.PageQueryEntity;
import com.abc.chenzeshenga.logistics.service.ReturnService;
import com.abc.chenzeshenga.logistics.service.returning.ReturnCommonService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
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
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

  @Resource
  private ReturnMapper returnMapper;

  private final ReturnService returnService;

  private final ReturnCommonService returnCommonService;

  private final CommonController commonController;

  @Autowired
  public ReturnController(
    CommonController commonController,
    ReturnService returnService,
    ReturnCommonService returnCommonService) {
    this.commonController = commonController;
    this.returnService = returnService;
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

  /**
   * 创建退货订单
   *
   * @param returnOrder 退货订单
   * @return 订单
   */
  @PostMapping("/add")
  public Json addReturnOrder(@RequestBody Return returnOrder) {
    String returnNo = returnOrder.getReturnNo();
    Return ori = returnMapper.selectByPk(returnNo);
    if (ori == null) {
      returnOrder.setStatus("新建");
      returnOrder.setUpdateOn(new Date());
      if (StringUtils.isEmpty(returnOrder.getCreateOnLbl())) {
        returnOrder.setCreateOn(new Date());
      } else {
        returnOrder.setCreateOn(DateUtil.getCustomDateFromDateStr(returnOrder.getCreateOnLbl()));
      }
      returnOrder.setUpdator(UserUtils.getUserName());
      returnMapper.newReturnOrd(returnOrder);
      List<ReturnContent> returnContentList = returnOrder.getContentList();
      if (returnContentList != null && !returnContentList.isEmpty()) {
        returnContentList.forEach(
          returnContent -> {
            returnContent.setUuid(SnowflakeIdWorker.generateStrId());
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
      ori.setToAddressLine1(returnOrder.getToAddressLine1());
      ori.setToAddressLine2(returnOrder.getToAddressLine2());
      ori.setToAddressLine3(returnOrder.getToAddressLine3());
      ori.setFromName(returnOrder.getFromName());
      ori.setFromContact(returnOrder.getFromContact());
      ori.setFromZipCode(returnOrder.getFromZipCode());
      ori.setFromAddressLine1(returnOrder.getFromAddressLine1());
      ori.setFromAddressLine2(returnOrder.getFromAddressLine2());
      ori.setFromAddressLine3(returnOrder.getFromAddressLine3());
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
          returnContent -> {
            returnContent.setUuid(SnowflakeIdWorker.generateStrId());
            String returnNoInContent = returnContent.getReturnNo();
            if (StringUtils.isBlank(returnNoInContent)) {
              returnContent.setReturnNo(returnOrder.getReturnNo());
            }
          });
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
      ori.setFromName(returnOrder.getFromName());
      ori.setFromContact(returnOrder.getFromContact());
      ori.setFromZipCode(returnOrder.getFromZipCode());
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

  @Deprecated
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
        returning.setToAddressLine3("日本岡山仓(okayama)");
        returning.setToName("东岳物流");
      });
    return Json.succ().data("page", returnPage);
  }

  /**
   * 分页查找退货订单
   *
   * @param returnPageQueryEntity 分页和查询参数
   * @return 退货订单数据
   */
  @PostMapping("/listV2")
  public Json listV2(@RequestBody PageQueryEntity<Return> returnPageQueryEntity) {
    PageData<Return> returnPageData = returnService.listV2(returnPageQueryEntity);
    return Json.succ().data("data", returnPageData);
  }

  /**
   * 根据退货单号删除退货单
   *
   * @param returnNo 退货单号
   * @return success
   */
  @GetMapping("/drop")
  public Json drop(@RequestParam String returnNo) {
    returnService.drop(returnNo);
    return Json.succ();
  }

  /**
   * 根据退货单号认领退货订单
   *
   * @param returnNo 退货单号
   * @return success
   */
  @GetMapping("/claim")
  public Json claimReturnOrd(@RequestParam String returnNo) {
    returnService.claimReturnOrd(returnNo);
    return Json.succ();
  }

  @PostMapping("/common/list")
  public Json listReturnOrd(
    @RequestBody String req, @RequestParam String type, @RequestParam String status)
    throws IOException {
    PageData<Return> returnPageData = returnCommonService.list(req, type, status);
    List<Return> returnList = returnPageData.getData();
    returnList.forEach(
      returning -> {
        if (StringUtils.isBlank(returning.getToAddressLine1())
          || StringUtils.isBlank(returning.getToAddressLine2())
          || StringUtils.isBlank(returning.getToAddressLine3())) {
          returning.setToAddressLine3("日本岡山仓(okayama)");
          returning.setToName("东岳物流");
        }
      });
    return Json.succ().data("page", returnPageData);
  }

  /**
   * 根据退货单号获取退货单详情
   *
   * @param returnNo 退货单号
   * @return 退货单
   */
  @GetMapping("/returnOrdDetail")
  public Json getReturnOrdDetail(@RequestParam String returnNo) {
    return Json.succ().data("returnOrd", returnService.getReturnOrdDetail(returnNo));
  }

  /**
   * 记录退货单包裹体积重量
   *
   * @param claimPackageList 退货单包裹列表
   * @return success
   */
  @PostMapping("/claimPkg")
  public Json updateClaimPackage(@RequestBody List<ClaimPackage> claimPackageList) {
    if (claimPackageList == null || claimPackageList.isEmpty()) {
      return Json.fail("请填写退货单包裹");
    }
    returnService.updateClaimPackage(claimPackageList);
    returnService.updateStatus(claimPackageList.get(0).getReturnNo(), "已收货");
    return Json.succ();
  }

  @PostMapping("/updateStatus")
  public Json updateClaimPackage(@RequestBody Map<String, String> request) {
    String returnNo = request.get("returnNo");
    String status = request.get("status");
    returnService.updateStatus(returnNo, status);
    return Json.succ();
  }

  /**
   * 更新退货单处理
   *
   * @param claimContentDealingList 退货单货物处理
   * @return success
   */
  @PostMapping("/claimContentDealing")
  public Json updateClaimContentDealing(
    @RequestBody List<ClaimContentDealing> claimContentDealingList) {
    if (claimContentDealingList == null || claimContentDealingList.isEmpty()) {
      return Json.fail("请填写退货单货物处理");
    }
    returnService.updateClaimContentDealing(claimContentDealingList);
    returnService.updateStatus(claimContentDealingList.get(0).getReturnNo(), "待用户确认");
    return Json.succ();
  }

  @GetMapping("/excel")
  public void getReturnOrd(HttpServletResponse httpServletResponse, Return returnOrd) {
  }
}
