package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.ChannelCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.*;
import com.abc.chenzeshenga.logistics.mapper.order.ManualOrderContentMapper;
import com.abc.chenzeshenga.logistics.mapper.shelf.UpShelfProductMapper;
import com.abc.chenzeshenga.logistics.mapper.warehouse.ProductOutWarehouseMapper;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.model.ord.OrdTrackNoMapping;
import com.abc.chenzeshenga.logistics.model.ord.OrderPackage;
import com.abc.chenzeshenga.logistics.model.shelf.UpShelfProduct;
import com.abc.chenzeshenga.logistics.model.warehouse.ProductOutWarehouse;
import com.abc.chenzeshenga.logistics.service.OrderService;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.*;
import com.abc.util.PageUtils;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Resource;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/ord")
public class OrderController {

    private static final String CARRIER = "carrier_";

    private static final String ONE = "1";

    private static final String THREE = "3";

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ManualOrderContentMapper manualOrderContentMapper;

    @Resource
    private LabelMapper labelMapper;

    @Resource
    private UpShelfProductMapper upShelfProductMapper;

    @Resource
    private ProductOutWarehouseMapper productOutWarehouseMapper;

    @Resource
    private ProductMapper productMapper;

    private final OrderService orderService;

    private final LabelCache labelCache;

    private final ChannelCache channelCache;

    private final UserCommonService userCommonService;

    @Autowired
    public OrderController(
        OrderService orderService,
        LabelCache labelCache,
        ChannelCache channelCache,
        UserCommonService userCommonService) {
        this.orderService = orderService;
        this.labelCache = labelCache;
        this.channelCache = channelCache;
        this.userCommonService = userCommonService;
    }

    @PostMapping("/detail")
    public Json getOrdDetail(@RequestBody Map<String, String> request) {
        ManualOrder manualOrder = orderMapper.getOrdDetail(request.get("ordNo"));
        manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
        manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
        List<ManualOrderContent> manualOrderContentList = manualOrder.getManualOrderContents();
        for (ManualOrderContent manualOrderContent : manualOrderContentList) {
            String dySku = manualOrderContent.getDySku();
            Product product = productMapper.selectProductBySku(dySku);
            manualOrderContent.setImgUrl(product.getImgPath());
            UpShelfProduct upShelfProduct =
                upShelfProductMapper.selectOneBySku(dySku, product.getCreatedBy());
            manualOrderContent.setShelfNo(upShelfProduct.getShelfNo());
        }
        return Json.succ().data(manualOrder);
    }

    @PostMapping("/add")
    public Json add(@RequestBody @Valid ManualOrder manualOrder, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errMsg = new StringBuilder();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                errMsg.append(objectError.getDefaultMessage()).append(";");
            }
            return Json.fail().msg(errMsg.toString());
        }
        manualOrder.setStatus("1");
        Date curr = new Date();
        manualOrder.setCreateOn(curr);
        manualOrder.setUpdateOn(curr);
        if (StringUtils.isBlank(manualOrder.getCreator())) {
            String cname = UserUtils.getUserName();
            manualOrder.setCreator(cname);
            manualOrder.setUpdator(cname);
        } else {
            manualOrder.setUpdator(manualOrder.getCreator());
        }
        if (StringUtils.isEmpty(manualOrder.getFromAddressLine1())) {
            manualOrder.setFromAddressLine3("日本岡山仓(okayama)");
        }
        if (StringUtils.isEmpty(manualOrder.getFromName())) {
            manualOrder.setFromName("东岳物流");
        }
        int result = orderMapper.add(manualOrder);
        List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
        if (manualOrderContents != null && !manualOrderContents.isEmpty()) {
            manualOrderContents.forEach(
                manualOrderContent -> {
                    manualOrderContent.setOrdno(manualOrder.getOrderNo());
                    manualOrderContent.setUuid(UuidUtils.uuid());
                });
            orderMapper.insertContent(manualOrderContents);
        }
        return Json.succ().data(result);
    }

    /**
     * 订单信息更新
     *
     * @param manualOrder 订单内容
     * @return 更新成功
     */
    @PostMapping("/update")
    public Json update(@RequestBody ManualOrder manualOrder) {
        String ordno = manualOrder.getOrderNo();
        ManualOrder manualOrderOri = orderMapper.selectById(ordno);
        List<OrderPackage> orderPackageList = manualOrder.getOrderPackageList();
        if (orderPackageList != null && !orderPackageList.isEmpty()) {
            orderMapper.dropOrderPackage(manualOrder.getOrderNo());
            orderPackageList.forEach(
                orderPackage -> orderPackage.setUuid(SnowflakeIdWorker.generateStrId()));
            orderMapper.insertOrderPackage(orderPackageList);
        }
        if ("2".equals(manualOrder.getStatus()) && "1".equals(manualOrderOri.getCategory())) {
            List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
            boolean satisfied = true;
            for (ManualOrderContent manualOrderContent : manualOrderContentList) {
                if (!manualOrderContent.getPicked().equals(manualOrderContent.getNum())) {
                    satisfied = false;
                    break;
                }
            }
            Date curr = new Date();
            manualOrder.setUpdateOn(curr);
            String cname = UserUtils.getUserName();
            manualOrder.setUpdator(cname);
            if (!satisfied) {
                orderMapper.update(manualOrder);
                return Json.succ("update ord fail", "该订单商品未完成拣货操作，基本信息已更新");
            } else {
                String status = manualOrder.getStatus();
                manualOrder.setStatus(String.valueOf(Integer.parseInt(status) + 1));
                updateOrdAndOrdContent(manualOrder);
                return Json.succ().data("订单已发货");
            }
        } else {
            updateOrdAndOrdContent(manualOrder);
            return Json.succ().data("订单信息更新");
        }
    }

    private void updateOrdAndOrdContent(@RequestBody ManualOrder manualOrder) {
        orderMapper.update(manualOrder);
        List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
        if (manualOrderContents != null && !manualOrderContents.isEmpty()) {
            orderMapper.deleteContent(manualOrder.getOrderNo());
            manualOrderContents.forEach(
                manualOrderContent -> {
                    manualOrderContent.setOrdno(manualOrder.getOrderNo());
                    manualOrderContent.setUuid(UuidUtils.uuid());
                });
            orderMapper.insertContent(manualOrderContents);
        }
    }

    @PostMapping("/updateOrd")
    public Json updateOrd(@RequestBody ManualOrder manualOrder) {
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        int result = orderMapper.update(manualOrder);
        List<OrderPackage> orderPackageList = manualOrder.getOrderPackageList();
        if (orderPackageList != null && !orderPackageList.isEmpty()) {
            orderMapper.dropOrderPackage(manualOrder.getOrderNo());
            orderPackageList.forEach(
                orderPackage -> orderPackage.setUuid(SnowflakeIdWorker.generateStrId()));
            orderMapper.insertOrderPackage(orderPackageList);
        }
        return Json.succ().data(result);
    }

    @PostMapping("/update/{ordno}")
    public Json update(@RequestBody Map<String, Object> data, @PathVariable String ordno) {
        ManualOrder manualOrder = new ManualOrder();
        manualOrder.setOrderNo(ordno);
        manualOrder.setTotalVolume((double) data.get("totalVolume"));
        manualOrder.setTotalWeight((double) data.get("totalWeight"));
        manualOrder.setOrdFee((String) data.get("ordFee"));
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        orderMapper.updateVolumeAndWeight(manualOrder);
        return Json.succ();
    }

    @PostMapping("/trackno")
    public Json fillInTrackNo(@RequestBody ManualOrder manualOrder) {
        log.info(manualOrder.toString());
        Date curr = new Date();
        manualOrder.setUpdateOn(curr);
        String cname = UserUtils.getUserName();
        manualOrder.setUpdator(cname);
        manualOrder.setCarrierNo(manualOrder.getCarrierNo().replace(CARRIER, ""));
        int result = orderMapper.fillInTrackNo(manualOrder);
        return Json.succ().data(result);
    }

    /**
     * 通过文件方式批量更新订单追踪单号
     *
     * @param multipartFile 文件信息
     * @return 更新成功
     * @throws IOException 读取文件异常
     */
    @PostMapping("/trackno/list")
    public Json fillInTrackNoList(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        List<Object> ordTrackNoMappingList = EasyExcelFactory.read(multipartFile.getInputStream(), new Sheet(1, 1));
        ordTrackNoMappingList.forEach(
            data -> {
                if (data instanceof List && ((List<?>) data).size() == 3) {
                    OrdTrackNoMapping ordTrackNoMapping = new OrdTrackNoMapping((String) ((List<?>) data).get(0), (String) ((List<?>) data).get(1), (String) ((List<?>) data).get(2));
                    orderMapper.updateTrackNo(ordTrackNoMapping);
                }
            }
        );
        log.info(ordTrackNoMappingList.toString());
        return Json.succ();
    }

    @PostMapping("/list/{type}/{status}")
    @SuppressWarnings("rawtypes")
    public Json list(
        @RequestBody String body, @PathVariable String type, @PathVariable String status) {
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Page<ManualOrder> manualOrderPage = orderService.listV2(page, cname, type, status);
        enrichOrd(manualOrderPage);
        return Json.succ().data("page", manualOrderPage);
    }

    @PostMapping("/v2/list/{type}/{status}")
    @SuppressWarnings("rawtypes")
    public Json listV2(
        @RequestBody String body,
        @PathVariable String type,
        @PathVariable String status,
        @RequestParam(required = false, defaultValue = "2000-01-01") String fromDate,
        @RequestParam(required = false, defaultValue = "2099-01-01") String toDate,
        @RequestParam(required = false) String ordno,
        @RequestParam(required = false) String creator,
        @RequestParam(required = false) String channelCode,
        @RequestParam(required = false) String trackNo,
        @RequestParam(required = false) String userCustomOrderNo,
        @RequestParam(required = false) int pickup)
        throws ParseException {
        String cname = UserUtils.getUserName();
        JSONObject jsonObject = JSON.parseObject(body);
        Page page = PageUtils.getPageParam(jsonObject);
        Date fromDate1 = DateUtil.getFromDateFromStr(fromDate);
        Date toDate1 = DateUtil.getToDateFromStr(toDate + " 23:59:59");
        Page<ManualOrder> manualOrderPage;
        if (userCommonService.isManagerRole(cname)) {
            manualOrderPage =
                orderService.listByRange(
                    page,
                    type,
                    status,
                    fromDate1,
                    toDate1,
                    ordno,
                    creator,
                    channelCode,
                    trackNo,
                    userCustomOrderNo,
                    pickup);
        } else {
            manualOrderPage =
                orderService.listByRange(
                    page,
                    type,
                    status,
                    fromDate1,
                    toDate1,
                    ordno,
                    cname,
                    channelCode,
                    trackNo,
                    userCustomOrderNo,
                    pickup);
        }
        enrichOrd(manualOrderPage);
        return Json.succ().data("page", manualOrderPage);
    }

    private void enrichOrd(Page<ManualOrder> manualOrderPage) {
        List<ManualOrder> manualOrderList = manualOrderPage.getRecords();
        for (ManualOrder manualOrder : manualOrderList) {
            enrichSingleOrd(manualOrder);
        }
    }

    private void enrichSingleOrd(ManualOrder manualOrder) {
        manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
        manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
        manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
        String channelDesc = channelCache.channelLabel(manualOrder.getChannel());
        if (StringUtils.isNotBlank(channelDesc)) {
            manualOrder.setChannelDesc(channelDesc);
        }
        String orderNo = manualOrder.getOrderNo();
        List<ManualOrderContent> manualOrderContentList =
            manualOrderContentMapper.getManualOrderContent(orderNo);
        double totalPrice = 0.0;
        for (ManualOrderContent manualOrderContent : manualOrderContentList) {
            String num = manualOrderContent.getNum();
            String price = manualOrderContent.getPrice();
            if (StringUtils.isBlank(num) || StringUtils.isBlank(price)) {
                continue;
            }
            totalPrice += Double.parseDouble(num) * Double.parseDouble(price);
        }
        Double finalPrice = totalPrice;
        for (ManualOrderContent content : manualOrderContentList) {
            content.setTotalPrice(finalPrice);
            String total = content.getNum();
            String picked = content.getPicked();
            try {
                if (!Double.valueOf(total).equals(Double.valueOf(picked))) {
                    manualOrder.setSatisfied(false);
                    break;
                }
            } catch (NumberFormatException numberFormatException) {
                log.info("manual order as {}", manualOrder);
                log.info("error stack info ", numberFormatException);
            }
        }
        manualOrder.setManualOrderContents(manualOrderContentList);
    }

    @GetMapping("/delete/{ordNo}")
    public Json delete(@PathVariable String ordNo) {
        orderMapper.delete(ordNo);
        orderMapper.deleteContent(ordNo);
        return Json.succ();
    }

    /**
     * 根据订单号获取订单明细
     *
     * @param ordNo 订单号
     * @return 订单信息
     */
    @GetMapping("/get/{ordNo}")
    public Json selectByPk(@PathVariable String ordNo) {
        ManualOrder manualOrder = orderMapper.selectById(ordNo);
        List<ManualOrderContent> manualOrderContentList = orderMapper.listContent2(ordNo);
        manualOrder.setManualOrderContents(manualOrderContentList);
        return Json.succ().data(manualOrder);
    }

    @GetMapping("/pickup/{regTxt}")
    public Json search(@PathVariable String regTxt) {
        List<ManualOrderContent> contentList = orderMapper.listContent(regTxt);
        contentList.forEach(
            manualOrderContent -> {
                String sku = manualOrderContent.getDySku();
                String orderNo = manualOrderContent.getOrdno();
                ManualOrder manualOrder = orderMapper.getOrdDetail(orderNo);
                UpShelfProduct upShelfProduct =
                    upShelfProductMapper.selectOneBySku(sku, manualOrder.getCreator());
                if (upShelfProduct != null) {
                    manualOrderContent.setShelfNo(upShelfProduct.getShelfNo());
                }
            });
        if (contentList.isEmpty()) {
            return Json.fail().msg("该订单无法拣货");
        }
        return Json.succ().data(contentList);
    }

    @PostMapping("/pickup")
    public Json pickup(@RequestBody List<ManualOrderContent> manualOrderContentList) {
        log.info(manualOrderContentList.toString());
        String ordno;
        if (!manualOrderContentList.isEmpty()) {
            ordno = manualOrderContentList.get(0).getOrdno();
            List<ManualOrderContent> currContentList =
                manualOrderContentMapper.getManualOrderContent(ordno);
            boolean satisfiedFlag = true;
            if (currContentList != null && !currContentList.isEmpty()) {
                for (int i = 0; i < currContentList.size(); i++) {
                    ManualOrderContent manualOrderContent = currContentList.get(i);
                    String num = manualOrderContent.getNum();
                    String picked = manualOrderContent.getPicked();
                    if (Integer.valueOf(num) > Integer.valueOf(picked)) {
                        satisfiedFlag = false;
                        break;
                    }
                }
            }
            if (satisfiedFlag) {
                orderMapper.update(new ManualOrder(ordno, 1));
                return Json.fail("pickup", "上一次拣货已将所有货物配齐，请勿重复拣货");
            }
            orderMapper.deleteContent(ordno);
            manualOrderContentList.forEach(
                manualOrderContent -> manualOrderContent.setUuid(SnowflakeIdWorker.generateStrId()));
            boolean satisfiedFlag2 = true;
            if (manualOrderContentList != null && !manualOrderContentList.isEmpty()) {
                for (int i = 0; i < manualOrderContentList.size(); i++) {
                    ManualOrderContent manualOrderContent = manualOrderContentList.get(i);
                    String num = manualOrderContent.getNum();
                    String picked = manualOrderContent.getPicked();
                    if (Integer.valueOf(num) > Integer.valueOf(picked)) {
                        satisfiedFlag2 = false;
                        break;
                    }
                }
            }
            if (satisfiedFlag2) {
                orderMapper.update(new ManualOrder(ordno, 1));
            }
            orderMapper.insertContent(manualOrderContentList);
            ManualOrder manualOrder = orderMapper.getOrdDetail(ordno);
            String category = manualOrder.getCategory();
            if ("1".equals(category)) {
                // 更新库存
                for (ManualOrderContent manualOrderContent : manualOrderContentList) {
                    String sku = manualOrderContent.getDySku();
                    int num = Integer.parseInt(manualOrderContent.getNum());
                    String owner = manualOrder.getCreator();
                    UpShelfProduct upShelfProduct = upShelfProductMapper.selectOneBySku(sku, owner);
                    if (upShelfProduct != null) {
                        upShelfProduct.setNum(String.valueOf(Integer.parseInt(upShelfProduct.getNum()) - num));
                        upShelfProductMapper.updateAllColumnById(upShelfProduct);
                        ProductOutWarehouse productOutWarehouse = new ProductOutWarehouse();
                        productOutWarehouse.setUuid(SnowflakeIdWorker.generateStrId());
                        productOutWarehouse.setDySku(manualOrderContent.getDySku());
                        productOutWarehouse.setSku(manualOrderContent.getSku());
                        productOutWarehouse.setNum(String.valueOf(num));
                        productOutWarehouse.setOwner(owner);
                        productOutWarehouse.setOrderNo(ordno);
                        productOutWarehouse.setOutTime(new Date());
                        productOutWarehouse.setHoursInWarehouse(
                            DateUtil.getHourPoor(new Date(), upShelfProduct.getUptime()));
                        productOutWarehouseMapper.insert(productOutWarehouse);
                    } else {
                        return Json.fail("拣货", "未在当前库存找到对应商品，请先更新库存");
                    }
                }
            }
        } else {
            return Json.fail();
        }
        return Json.succ();
    }

    @GetMapping("/update/{category}/{ordno}/{status}")
    public Json statusUpdate(
        @PathVariable String category, @PathVariable String ordno, @PathVariable String status) {
        if (ONE.equals(category) && THREE.equals(status)) {
            List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
            if (whetherPickup(manualOrderContentList)) {
                return Json.fail().msg("有商品未拣货完成");
            }
        }
        ManualOrder manualOrder = new ManualOrder();
        manualOrder.setOrderNo(ordno);
        manualOrder.setStatus(status);
        orderMapper.statusUpdate(manualOrder);
        return Json.succ();
    }

    @PostMapping("/update/{category}/{status}")
    public Json batchStatusUpdate(
        @RequestBody List<String> ords, @PathVariable String category, @PathVariable String status) {
        if (ONE.equals(category) && THREE.equals(status)) {
            List<ManualOrderContent> manualOrderContentList = orderMapper.listContentBatch(ords);
            if (whetherPickup(manualOrderContentList)) {
                return Json.fail().msg("有商品未拣货完成");
            }
        }
        Map<String, Object> request = new HashMap<>(2);
        request.put("status", status);
        request.put("ords", ords);
        orderMapper.statusUpdateBatch(request);
        return Json.succ();
    }

    @PostMapping("/update/force/{category}/{status}")
    public Json batchForceStatusUpdate(
        @RequestBody List<String> ords, @PathVariable String category, @PathVariable String status) {
        Map<String, Object> request = new HashMap<>(2);
        request.put("status", status);
        request.put("ords", ords);
        orderMapper.statusUpdateBatch(request);
        return Json.succ();
    }

    private boolean whetherPickup(List<ManualOrderContent> manualOrderContentList) {
        boolean satisfied = false;
        for (ManualOrderContent manualOrderContent : manualOrderContentList) {
            if (manualOrderContent.isSatisfied()) {
                satisfied = true;
            } else {
                satisfied = false;
                break;
            }
        }
        return !satisfied;
    }

    @GetMapping("/carrier/distinct")
    public Json getCarrierList() {
        List<Label> carrierList = labelMapper.listCarrier();
        List<CommonLabel> commonLabelList = new ArrayList<>(4);
        carrierList.forEach(
            carrier -> {
                CommonLabel commonLabel = new CommonLabel();
                commonLabel.setLabel(carrier.getValue());
                commonLabel.setValue(carrier.getKey());
                commonLabelList.add(commonLabel);
            });
        return Json.succ().data(commonLabelList);
    }

    @PostMapping("/abnormal")
    public Json abnormal(@RequestBody ManualOrder manualOrder) {
        orderMapper.abnormal(manualOrder);
        return Json.succ();
    }

    @GetMapping("/getVolumeAndWeight/{ordno}")
    public Json getVolumeAndWeight(@PathVariable String ordno) {
        List<ManualOrderContent> manualOrderContentList = orderMapper.listContent(ordno);
        Map<String, Double> result = new HashMap<>(2);
        AtomicReference<Double> totalVolume = new AtomicReference<>(0.0);
        AtomicReference<Double> totalWeight = new AtomicReference<>(0.0);
        manualOrderContentList.forEach(
            manualOrderContent -> {
                String sku = manualOrderContent.getSku();
                Product product = orderMapper.getProduct(sku.split("/")[0]);
                totalVolume.updateAndGet(
                    v ->
                        v
                            + Double.valueOf(product.getLength())
                            * Double.valueOf(product.getHeight())
                            * Double.valueOf(product.getWidth())
                            * Double.valueOf(manualOrderContent.getNum()));
                totalWeight.updateAndGet(
                    v ->
                        v
                            + Double.valueOf(product.getWeight())
                            * Double.valueOf(manualOrderContent.getNum()));
            });
        result.put("totalVolume", totalVolume.get());
        result.put("totalWeight", totalWeight.get());
        return Json.succ().data(result);
    }

    @GetMapping("/quickSearch/{search}")
    public Json quickSearch(@PathVariable String search) {
        log.info(search);
        ManualOrder manualOrder = orderMapper.quickSearch(search);
        if (manualOrder != null) {
            log.info(manualOrder.toString());
            return Json.succ().data(manualOrder);
        } else {
            return Json.succ();
        }
    }

    @PostMapping("/fetchOrderNo")
    public Json fetchOrderNo(@RequestBody Map<String, String> request) {
        String creator = request.get("creator");
        List<ManualOrder> manualOrderList = orderMapper.fetchOrderNo(creator);
        return Json.succ().data(manualOrderList);
    }

    @GetMapping("/ordContent")
    public Json getOrdContentByOrdNo(@RequestParam String ordNo) {
        ManualOrder manualOrder = orderMapper.getOrdDetail(ordNo);
        enrichSingleOrd(manualOrder);
        return Json.succ().data(manualOrder);
    }

    @GetMapping("/userFee/generateTemplate")
    public Json downloadOrdUseFeeTemplate() throws FileNotFoundException {
        return Json.succ().data("data", orderService.generateTemplate());
    }

    @PostMapping("/batch/import")
    public Json batchImport(@RequestBody MultipartFile file) throws IOException {
        orderService.batchImport(file);
        return Json.succ();
    }

    @GetMapping("/userFee/confirm/{orderNo}")
    public Json confirm(@PathVariable String orderNo) {
        orderService.feeConfirm(orderNo);
        return Json.succ();
    }

}
