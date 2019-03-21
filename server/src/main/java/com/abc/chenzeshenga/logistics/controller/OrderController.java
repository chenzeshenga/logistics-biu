package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.ChannelCache;
import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.service.OrderService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j @RestController @RequestMapping("/ord") public class OrderController {

    private static final String CARRIER = "carrier_";

    private static final String ONE = "1";

    private static final String THREE = "3";

    @Resource private OrderMapper orderMapper;

    private OrderService orderService;

    private JapanAddressCache japanAddressCache;

    private LabelCache labelCache;

    private ChannelCache channelCache;

    private TrackNoController trackNoController;

    @Autowired
    public OrderController(OrderService orderService, JapanAddressCache japanAddressCache, LabelCache labelCache, ChannelCache channelCache,
        TrackNoController trackNoController) {
        this.orderService = orderService;
        this.japanAddressCache = japanAddressCache;
        this.labelCache = labelCache;
        this.channelCache = channelCache;
        this.trackNoController = trackNoController;
    }

    @PostMapping @RequestMapping("/add") public Json add(@RequestBody @Valid ManualOrder manualOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errMsg.append(objectError.getDefaultMessage()).append(";");
            }
            return Json.fail().msg(errMsg.toString());
        }
        setAddress(manualOrder);
        manualOrder.setStatus("1");
        Date curr = new Date();
        manualOrder.setCreateOn(curr);
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setCreator(cname);
        manualOrder.setUpdator(cname);
        if (StringUtils.isEmpty(manualOrder.getFromKenId())) {
            manualOrder.setFromKenId("");
            manualOrder.setFromCityId("");
            manualOrder.setFromTownId("");
            manualOrder.setFromDetailAddress("日本岡山仓(okayama)");
        }
        if (StringUtils.isEmpty(manualOrder.getFromName())) {
            manualOrder.setFromName("东岳物流");
        }
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

    @PostMapping @RequestMapping("/update/{ordno}") public Json update(@RequestBody Map<String, String> data, @PathVariable String ordno) {
        ManualOrder manualOrder = new ManualOrder();
        manualOrder.setOrderNo(ordno);
        manualOrder.setTotalVolume(data.get("totalVolume"));
        manualOrder.setTotalWeight(data.get("totalWeight"));
        manualOrder.setOrdFee(data.get("ordFee"));
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        orderMapper.updateVolumeAndWeight(manualOrder);
        return Json.succ();
    }

    @PostMapping @RequestMapping("/trackno") public Json fillInTrackNo(@RequestBody ManualOrder manualOrder) {
        log.info(manualOrder.toString());
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        manualOrder.setCarrierNo(manualOrder.getCarrierNo().replace(CARRIER, ""));
        int result = orderMapper.fillInTrackNo(manualOrder);
        return Json.succ().data(result);
    }

    @SuppressWarnings("unchecked") @PostMapping @RequestMapping("/trackno/list") public Json fillInTrackNoList(@RequestBody Map ords) {
        List<String> ordNos = (List<String>)ords.get("ords");
        String carrierNo = ((String)ords.get("carrierNo")).replace(CARRIER, "");
        Date curr = new Date();
        String cname = UserUtils.getUserName();
        ordNos.forEach(ordNo -> {
            ManualOrder manualOrder = new ManualOrder();
            manualOrder.setOrderNo(ordNo);
            manualOrder.setCarrierNo(carrierNo);
            manualOrder.setUpdator(cname);
            manualOrder.setUpdateOn(curr);
            manualOrder.setTrackNo(String.valueOf(trackNoController.generate().get("data")));
            orderMapper.fillInTrackNo(manualOrder);
        });
        return Json.succ();
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
        enrichOrd(manualOrderPage);
        return Json.succ().data("page", manualOrderPage);
    }

    @PostMapping @RequestMapping("/list/{type}/{status}/{fromDate}/{toDate}")
    public Json listByRange(@RequestBody String body, @PathVariable String type, @PathVariable String status, @PathVariable String fromDate,
        @PathVariable String toDate) throws ParseException {
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Date fromDate1 = DateUtil.getDateFromStr(fromDate);
        Date toDate1 = DateUtil.getDateFromStr(toDate);
        Page<ManualOrder> manualOrderPage = orderService.listByRange(page, cname, type, status, fromDate1, toDate1);
        enrichOrd(manualOrderPage);
        return Json.succ().data("page", manualOrderPage);
    }

    private void enrichOrd(Page<ManualOrder> manualOrderPage) {
        List<ManualOrder> manualOrderList = manualOrderPage.getRecords();
        manualOrderList.forEach(manualOrder -> {
            if (StringUtils.isEmpty(manualOrder.getFromKenId())) {
                manualOrder.setFromAddressDesc(manualOrder.getFromDetailAddress());
            } else {
                JpDetailAddress from = japanAddressCache
                    .getJpDetailAddress(Integer.valueOf(manualOrder.getFromKenId()), Integer.valueOf(manualOrder.getFromCityId()),
                        Integer.valueOf(manualOrder.getFromTownId()));
                manualOrder.setFromKenName(from.getKenName());
                manualOrder.setFromCityName(from.getCityName());
                manualOrder.setFromTownName(from.getTownName());
                manualOrder.setFromAddressDesc(from.getKenName() + from.getCityName() + from.getTownName() + manualOrder.getFromDetailAddress());
            }
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
            manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
            List<ManualOrderContent> manualOrderContentList = manualOrder.getManualOrderContents();
            double totalPrice = 0.0;
            for (ManualOrderContent manualOrderContent : manualOrderContentList) {
                totalPrice += Double.valueOf(manualOrderContent.getNum()) * Double.valueOf(manualOrderContent.getPrice());
            }
            final Double finalPrice = totalPrice;
            manualOrderContentList.forEach(content -> content.setTotalPrice(finalPrice));
        });
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
            address = new HashMap<>(3);
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
            toAddress = new HashMap<>(3);
        }
        toAddress.put("ken", manualOrder.getToKenId());
        toAddress.put("city", manualOrder.getToCityId());
        toAddress.put("town", manualOrder.getToTownId());
        return Json.succ().data(manualOrder);

    }

    @GetMapping @RequestMapping("/pickup/{regTxt}") public Json search(@PathVariable String regTxt) {
        List<ManualOrderContent> contentList = orderMapper.listContent(regTxt);
        if (contentList.isEmpty()) {
            return Json.fail().msg("该订单无法拣货");
        }
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

    @GetMapping @RequestMapping("/update/{category}/{ordno}/{status}")
    public Json statusUpdate(@PathVariable String category, @PathVariable String ordno, @PathVariable String status) {
        if (ONE.equals(category) && THREE.equals(status)) {
            List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
            boolean satisfied = false;
            for (ManualOrderContent manualOrderContent : manualOrderContentList) {
                if (manualOrderContent.isSatisfied()) {
                    satisfied = true;
                } else {
                    satisfied = false;
                    break;
                }
            }
            if (!satisfied) {
                return Json.fail().msg("有商品未拣货完成");
            }
        }
        ManualOrder manualOrder = new ManualOrder();
        manualOrder.setOrderNo(ordno);
        manualOrder.setStatus(status);
        orderMapper.statusUpdate(manualOrder);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/carrier/distinct") public Json getCarrierList() {
        List<Label> carrierList = labelCache.getLabelList(CARRIER);
        List<CommonLabel> commonLabelList = new ArrayList<>(4);
        carrierList.forEach(carrier -> {
            CommonLabel commonLabel = new CommonLabel();
            commonLabel.setLabel(carrier.getValue());
            commonLabel.setValue(carrier.getKey());
            commonLabelList.add(commonLabel);
        });
        return Json.succ().data(commonLabelList);
    }

    @PostMapping @RequestMapping("/abnormal") public Json abnormal(@RequestBody ManualOrder manualOrder) {
        orderMapper.abnormal(manualOrder);
        return Json.succ();
    }

    @GetMapping @RequestMapping("/getVolumeAndWeight/{ordno}") public Json getVolumeAndWeight(@PathVariable String ordno) {
        List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
        Map<String, Double> result = new HashMap<>(2);
        AtomicReference<Double> totalVolume = new AtomicReference<>(0.0);
        AtomicReference<Double> totalWeight = new AtomicReference<>(0.0);
        manualOrderContentList.forEach(manualOrderContent -> {
            String sku = manualOrderContent.getSku();
            Product product = orderMapper.getProduct(sku.split("/")[0]);
            totalVolume.updateAndGet(
                v -> v + Double.valueOf(product.getLength()) * Double.valueOf(product.getHeight()) * Double.valueOf(product.getWidth()) * Double
                    .valueOf(manualOrderContent.getNum()));
            totalWeight.updateAndGet(v -> v + Double.valueOf(product.getWeight()) * Double.valueOf(manualOrderContent.getNum()));
        });
        result.put("totalVolume", totalVolume.get());
        result.put("totalWeight", totalWeight.get());
        return Json.succ().data(result);
    }

}
