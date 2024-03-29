package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.*;
import com.abc.chenzeshenga.logistics.mapper.order.ManualOrderContentMapper;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.ord.ManualOrderV2;
import com.abc.chenzeshenga.logistics.model.ord.OrderPackage;
import com.abc.chenzeshenga.logistics.service.user.UserCommonService;
import com.abc.chenzeshenga.logistics.util.*;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.Page;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzesheng
 * @version 1.0
 */
@RestController
@Slf4j
public class CommonController {

    private static final String CARRIER = "carrier_";

    @Resource
    private ImgMapper imgMapper;

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private ManualOrderContentMapper manualOrderContentMapper;

    @Resource
    private WarehousingMapper warehousingMapper;

    @Resource
    private FileMapper fileMapper;

    @Resource
    private UserFileRecordMapper userFileRecordMapper;

    @Resource
    private ProductMapper productMapper;

    private final LabelCache labelCache;

    private final UserCommonService userCommonService;

    @Autowired
    public CommonController(LabelCache labelCache, UserCommonService userCommonService) {
        this.labelCache = labelCache;
        this.userCommonService = userCommonService;
    }

    @GetMapping("/generate/pk")
    public Json getOrderNo() {
        return Json.succ().data(String.valueOf(SnowflakeIdWorker.generateId()));
    }

    @GetMapping("/generate/pk/warehousing")
    public Json getWarehousingNo() {
        return Json.succ().data(String.valueOf(SnowflakeIdWorker.generateId()));
    }

    @GetMapping("/generate/pk/returning")
    public Json getReturnNo() {
        return Json.succ().data(String.valueOf(SnowflakeIdWorker.generateId()));
    }

    @GetMapping("/generate/sku")
    public Json getDySku() {
        String sku = SkuUtil.generateDySku();
        return Json.succ().data(sku);
    }

    @RequestMapping(value = "/img/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getImg(@PathVariable String uuid) {
        Img img = imgMapper.selectByPrimaryKey(uuid);
        return img.getImg();
    }

    @GetMapping(value = "/template/file/{uuid}")
    @ResponseBody
    public void file(@PathVariable String uuid, HttpServletResponse httpServletResponse)
            throws IOException {
        File file = fileMapper.selectByPrimaryKey(uuid);
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(file.getFileName(), "utf-8"));
        httpServletResponse.getOutputStream().write(file.getUserFile());
        httpServletResponse.flushBuffer();
    }

    /**
     * @deprecated
     */
    @Deprecated
    @GetMapping(value = "/file/{uuid}")
    @ResponseBody
    public void commonFile(@PathVariable String uuid, HttpServletResponse httpServletResponse)
            throws IOException {
        File file = fileMapper.selectByPrimaryKeyWithName(uuid);
        if (file == null) {
            httpServletResponse.getOutputStream().write("no file related".getBytes());
            httpServletResponse.flushBuffer();
        } else {
            httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            httpServletResponse.setHeader(
                    "Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(file.getFileName(), "utf-8"));
            httpServletResponse.getOutputStream().write(file.getUserFile());
            httpServletResponse.flushBuffer();
        }
    }

    @PostMapping
    @RequestMapping(value = "/img")
    public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile, String uuid)
            throws IOException {
        Img img = new Img(uuid, multipartFile.getBytes());
        imgMapper.insert(img);
        return Json.succ();
    }

    @PostMapping("/file/order")
    public Json putFile2OrderRecord(
            @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam String orderNo)
            throws IOException {
        File file = new File();
        String md5 = DigestUtils.md5Hex(multipartFile.getBytes());
        file.setFileName(multipartFile.getOriginalFilename());
        file.setUserFile(multipartFile.getBytes());
        String fileSign = md5 + System.currentTimeMillis();
        file.setUuid(fileSign);
        fileMapper.insert(file);
        ManualOrder manualOrder = orderMapper.getOrdDetail(orderNo);
        String files = manualOrder.getFiles();
        if (StringUtils.isBlank(files)) {
            files = "";
            files += fileSign;
        } else {
            files += ";" + fileSign;
        }
        manualOrder.setFiles(files);
        orderMapper.update(manualOrder);
        return Json.succ();
    }

    @GetMapping("/ord/excel/{category}/{status}")
    public void getOrdExcel(
            HttpServletResponse httpServletResponse,
            @PathVariable String category,
            @PathVariable String status,
            @RequestParam(required = false, defaultValue = "2000-01-01") String fromDate,
            @RequestParam(required = false, defaultValue = "2099-01-01") String toDate,
            @RequestParam(required = false, defaultValue = "") String ordno,
            @RequestParam(required = false, defaultValue = "") String creator,
            @RequestParam(required = false, defaultValue = "") String channelCode,
            @RequestParam(required = false, defaultValue = "") String trackNo,
            @RequestParam(required = false, defaultValue = "") String userCustomOrderNo,
            @RequestParam(required = false) int pickup)
            throws IOException, ParseException {
        String fileName = "订单状态.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        List<ManualOrder> manualOrderList;
        Date fromDate1 = DateUtil.getFromDateFromStr(fromDate);
        Date toDate1 = DateUtil.getToDateFromStr(toDate + " 23:59:59");
        if (userCommonService.isManagerRole(UserUtils.getUserName())) {
            manualOrderList =
                    orderMapper.listByRangeWithoutPage(
                            category,
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
            manualOrderList =
                    orderMapper.listByRangeWithoutPage(
                            category,
                            status,
                            fromDate1,
                            toDate1,
                            ordno,
                            UserUtils.getUserName(),
                            channelCode,
                            trackNo,
                            userCustomOrderNo,
                            pickup);
        }
        manualOrderList.forEach(
                manualOrder -> {
                    manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
                    manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
                    manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
                    manualOrder.setCollectDesc("false".equals(manualOrder.getCollect()) ? "否" : "是");
                    List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
                    if (!manualOrderContents.isEmpty()) {
                        ManualOrderContent manualOrderContent1 = manualOrderContents.get(0);
                        if (ObjectUtils.anyNotNull(manualOrderContent1)) {
                            manualOrder.setSku1(manualOrderContent1.getDySku());
                            manualOrder.setName1(manualOrderContent1.getName());
                            manualOrder.setNum1(manualOrderContent1.getNum());
                        }
                    }
                    if (manualOrderContents.size() >= 2) {
                        ManualOrderContent manualOrderContent2 = manualOrderContents.get(1);
                        if (ObjectUtils.anyNotNull(manualOrderContent2)) {
                            manualOrder.setSku2(manualOrderContent2.getDySku());
                            manualOrder.setName2(manualOrderContent2.getName());
                            manualOrder.setNum2(manualOrderContent2.getNum());
                        }
                    }
                    if (manualOrderContents.size() >= 3) {
                        ManualOrderContent manualOrderContent3 = manualOrderContents.get(2);
                        if (ObjectUtils.anyNotNull(manualOrderContent3)) {
                            manualOrder.setSku3(manualOrderContent3.getDySku());
                            manualOrder.setName3(manualOrderContent3.getName());
                            manualOrder.setNum3(manualOrderContent3.getNum());
                        }
                    }
                });
        writeServletResp(httpServletResponse, manualOrderList, ManualOrder.class);
    }

    @GetMapping("/ord/excel/v2/{category}/{status}")
    public void getOrdExcelV2(
            HttpServletResponse httpServletResponse,
            @PathVariable String category,
            @PathVariable String status,
            @RequestParam(required = false, defaultValue = "2000-01-01") String fromDate,
            @RequestParam(required = false, defaultValue = "2099-01-01") String toDate,
            @RequestParam(required = false, defaultValue = "") String ordno,
            @RequestParam(required = false, defaultValue = "") String channelCode,
            @RequestParam(required = false, defaultValue = "") String trackNo,
            @RequestParam(required = false, defaultValue = "") String userCustomOrderNo,
            @RequestParam(required = false, defaultValue = "") int pickup,
            @RequestParam(required = false, defaultValue = "") String creator)
            throws IOException, ParseException {
        String fileName = "订单" + MyDateUtils.getCurrDateStr() + ".xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        List<ManualOrderV2> manualOrderList;
        List<ManualOrderContent> manualOrderContentList = new ArrayList<>();
        List<OrderPackage> orderPackageList = new ArrayList<>();
        if (userCommonService.isManagerRole(UserUtils.getUserName())) {
            Date fromDate1 = DateUtil.getFromDateFromStr(fromDate);
            Date toDate1 = DateUtil.getToDateFromStr(toDate + " 23:59:59");
            manualOrderList =
                    orderMapper.listByRangeWithoutPageV2(
                            category,
                            status,
                            fromDate1,
                            toDate1,
                            ordno,
                            creator,
                            channelCode,
                            trackNo,
                            userCustomOrderNo,
                            pickup);
            Map<String, Object> params = new HashMap<>();
            params.put("category", category);
            params.put("status", status);
            params.put("ordno", ordno);
            params.put("creator", creator);
            params.put("channelCode", channelCode);
            params.put("trackNo", trackNo);
            params.put("userCustomOrderNo", userCustomOrderNo);
            params.put("pickup", pickup);
            params.put("fromDate", fromDate1);
            params.put("toDate", toDate1);
            manualOrderContentList =
                    manualOrderContentMapper.listManualOrderContentByParamsWithoutPage(params);
            // todo
            orderPackageList = orderMapper.listPackageByCategoryAndStatusUser(category, status, creator);
        } else {
            Date fromDate1 = DateUtil.getFromDateFromStr(fromDate);
            Date toDate1 = DateUtil.getToDateFromStr(toDate + " 23:59:59");
            manualOrderList =
                    orderMapper.listByRangeWithoutPageV2(
                            category,
                            status,
                            fromDate1,
                            toDate1,
                            ordno,
                            UserUtils.getUserName(),
                            channelCode,
                            trackNo,
                            userCustomOrderNo,
                            pickup);
        }
        manualOrderList.forEach(
                manualOrder -> {
                    manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
                    manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
                    manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
                    manualOrder.setCollectDesc("false".equals(manualOrder.getCollectDesc()) ? "否" : "是");
                });

        writeServletRespV2(
                httpServletResponse, manualOrderList, manualOrderContentList, orderPackageList);
    }

    private void writeServletRespV2(
            HttpServletResponse httpServletResponse,
            List<ManualOrderV2> manualOrderList,
            List<ManualOrderContent> manualOrderContentList,
            List<OrderPackage> orderPackageList)
            throws IOException {
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, ManualOrderV2.class);
        Sheet sheet2 = new Sheet(2, 0, ManualOrderContent.class);
        Sheet sheet3 = new Sheet(3, 0, OrderPackage.class);
        excelWriter.write(manualOrderList, sheet1);
        excelWriter.write(manualOrderContentList, sheet2);
        excelWriter.write(orderPackageList, sheet3);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    private void writeServletResp(
            HttpServletResponse httpServletResponse,
            List<? extends BaseRowModel> baseRowModels,
            Class<? extends BaseRowModel> clazz)
            throws IOException {
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, clazz);
        excelWriter.write(baseRowModels, sheet1);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    @GetMapping("/product/excel/{status}")
    public void getProductExcel(HttpServletResponse httpServletResponse, @PathVariable String status)
            throws IOException {
        String fileName = "商品信息.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        Product searchProduct = new Product();
        searchProduct.setStatus(status);
        searchProduct.setCreatedBy(UserUtils.getUserName());
        List<Product> productList =
                productMapper.listByStatusWithUser(searchProduct, new SqlLimit(1, 500));
        productList.forEach(
                product -> {
                    if (StringUtils.isEmpty(product.getCategoryName())) {
                        product.setCategoryName(labelCache.getLabel("classification_" + product.getCategory()));
                    }
                    if ("0".equals(product.getStatus())) {
                        product.setStatusDesc("审核中");
                    } else if ("1".equals(product.getStatus())) {
                        product.setStatusDesc("在库");
                    }
                });
        writeServletResp(httpServletResponse, productList, Product.class);
    }

    @PostMapping("/ord/excel")
    public Json parseExcel(
            @RequestParam(value = "file") MultipartFile multipartFile, @RequestParam String user)
            throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes());
        fileMapper.insert(file);
        String username = UserUtils.getUserName();
        UserFileRecord userFileRecord =
                new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
        userFileRecord.setUid(username);
        userFileRecord.setFileUuid(uuid);
        userFileRecordMapper.insert(userFileRecord);
        InputStream inputStream = multipartFile.getInputStream();
        List<Object> manualOrderList =
                EasyExcelFactory.read(inputStream, new Sheet(1, 0, ManualOrder4Input.class));
        manualOrderList.forEach(
                manualOrder -> {
                    if (manualOrderList.indexOf(manualOrder) >= 1) {
                        ManualOrder4Input manualOrder4Input;
                        if (manualOrder instanceof ManualOrder4Input) {
                            manualOrder4Input = (ManualOrder4Input) manualOrder;
                            log.info(manualOrder4Input.toString());
                            ManualOrder manualOrder4Database = new ManualOrder();
                            if (!StringUtils.isBlank(manualOrder4Input.getUserCustomOrderNo())) {
                                manualOrder4Database.setUserCustomOrderNo(manualOrder4Input.getUserCustomOrderNo());
                            }
                            String orderno = SnowflakeIdWorker.generateStrId();
                            manualOrder4Database.setOrderNo(orderno);
                            manualOrder4Database.setCreator(user);
                            manualOrder4Database.setUpdator(UserUtils.getUserName());
                            Date curr = new Date();
                            manualOrder4Database.setCreateOn(curr);
                            manualOrder4Database.setUpdateOn(curr);
                            manualOrder4Database.setStatus("1");
                            if (StringUtils.isNotBlank(manualOrder4Input.getCategoryName())) {
                                manualOrder4Database.setCategory(
                                        switchOrderCategoryFromCategoryName(manualOrder4Input.getCategoryName()));
                            } else {
                                manualOrder4Database.setCategory("1");
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getChannelCode())) {
                                manualOrder4Database.setChannel(manualOrder4Input.getChannelCode());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getFromAddressLine1())) {
                                manualOrder4Database.setFromAddressLine1(manualOrder4Input.getFromAddressLine1());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getFromAddressLine2())) {
                                manualOrder4Database.setFromAddressLine2(manualOrder4Input.getFromAddressLine2());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getFromAddressLine3())) {
                                manualOrder4Database.setFromAddressLine3(manualOrder4Input.getFromAddressLine3());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getFromName())) {
                                manualOrder4Database.setFromName(manualOrder4Input.getFromName());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getFromContact())) {
                                manualOrder4Database.setFromContact(manualOrder4Input.getFromContact());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToName())) {
                                manualOrder4Database.setToName(manualOrder4Input.getToName());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToContact())) {
                                manualOrder4Database.setToContact(manualOrder4Input.getToContact());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToAddressLine1())) {
                                manualOrder4Database.setToAddressLine1(manualOrder4Input.getToAddressLine1());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToAddressLine2())) {
                                manualOrder4Database.setToAddressLine2(manualOrder4Input.getToAddressLine2());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToAddressLine3())) {
                                manualOrder4Database.setToAddressLine3(manualOrder4Input.getToAddressLine3());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getToZipCode())) {
                                manualOrder4Database.setToZipCode(manualOrder4Input.getToZipCode());
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getCollectDesc())) {
                                manualOrder4Database.setCollect(
                                        "是".equals(manualOrder4Input.getCollectDesc()) ? "true" : "false");
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getCollectNum())) {
                                manualOrder4Database.setCollectNum(manualOrder4Input.getCollectNum());
                            }
                            List<ManualOrderContent> manualOrderContentList = new ArrayList<>();
                            if (StringUtils.isNotBlank(manualOrder4Input.getSku1())
                                    && StringUtils.isNotBlank(manualOrder4Input.getNum1())) {
                                ManualOrderContent manualOrderContent1 = new ManualOrderContent();
                                manualOrderContent1.setUuid(SnowflakeIdWorker.generateStrId());
                                manualOrderContent1.setOrdno(orderno);
                                manualOrderContent1.setDySku(manualOrder4Input.getSku1());
                                manualOrderContent1.setName(manualOrder4Input.getName1());
                                manualOrderContent1.setNum(manualOrder4Input.getNum1());
                                manualOrderContentList.add(manualOrderContent1);
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getSku2())
                                    && StringUtils.isNotBlank(manualOrder4Input.getNum2())) {
                                ManualOrderContent manualOrderContent2 = new ManualOrderContent();
                                manualOrderContent2.setUuid(SnowflakeIdWorker.generateStrId());
                                manualOrderContent2.setOrdno(orderno);
                                manualOrderContent2.setDySku(manualOrder4Input.getSku2());
                                manualOrderContent2.setName(manualOrder4Input.getName2());
                                manualOrderContent2.setNum(manualOrder4Input.getNum2());
                                manualOrderContentList.add(manualOrderContent2);
                            }
                            if (StringUtils.isNotBlank(manualOrder4Input.getSku3())
                                    && StringUtils.isNotBlank(manualOrder4Input.getNum3())) {
                                ManualOrderContent manualOrderContent3 = new ManualOrderContent();
                                manualOrderContent3.setUuid(SnowflakeIdWorker.generateStrId());
                                manualOrderContent3.setOrdno(orderno);
                                manualOrderContent3.setDySku(manualOrder4Input.getSku3());
                                manualOrderContent3.setName(manualOrder4Input.getName3());
                                manualOrderContent3.setNum(manualOrder4Input.getNum3());
                                manualOrderContentList.add(manualOrderContent3);
                            }
                            if (!manualOrderContentList.isEmpty()) {
                                orderMapper.insertContent(manualOrderContentList);
                            }
                            orderMapper.add(manualOrder4Database);
                        }
                    }
                });
        return Json.succ();
    }

    @PostMapping("/ord/barcode/scan")
    public Json scan(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String result = ZxingBarcodeUtils.decode(multipartFile.getInputStream());
        if (StringUtils.isEmpty(result)) {
            return Json.succ("scan", "未识别的条形码");
        } else {
            return Json.succ("scan", result);
        }
    }

    @PostMapping("/product/barcode/scan")
    public Json productBarcode(@RequestParam(value = "file") MultipartFile multipartFile)
            throws IOException {
        String result = ZxingBarcodeUtils.decode(multipartFile.getInputStream());
        if (StringUtils.isEmpty(result)) {
            return Json.succ("scan", "未识别的商品条形码");
        } else {
            return Json.succ("scan", result);
        }
    }

    @PostMapping("/product/excel")
    public Json parseProductExcel(
            @RequestParam(value = "file") MultipartFile multipartFile,
            @RequestParam(required = false, defaultValue = "") String standFor)
            throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes());
        fileMapper.insert(file);
        String username = UserUtils.getUserName();
        if (StringUtils.isNotEmpty(standFor)) {
            username = standFor;
        }
        UserFileRecord userFileRecord =
                new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
        userFileRecord.setUid(username);
        userFileRecord.setFileUuid(uuid);
        userFileRecordMapper.insert(userFileRecord);
        InputStream inputStream = multipartFile.getInputStream();
        List<Object> productList = EasyExcelFactory.read(inputStream, new Sheet(1, 0, Product.class));
        //  ignore the first item since it's the Excel head
        for (int i = 1; i < productList.size(); i++) {
            Product product = (Product) productList.get(i);
            try {
                if (StringUtils.isBlank(product.getSku())) {
                    product.setSku(SkuUtil.generateDySku());
                }
                product.setCategory(switchFromCategoryName(product.getCategoryName()));
                product.setDySku(SkuUtil.generateDySku());
                product.setStatus("0");
                product.setCreatedBy(username);
                product.setUpdateBy(username);
                Date curr = new Date();
                product.setUpdateOn(curr);
                product.setCreateOn(curr);
                productMapper.insertSelective(product);
            } catch (Exception e) {
                log.error("error data {}", product);
                log.error("error stack info", e);
            }
        }
        return Json.succ();
    }

    private String switchFromCategoryName(String categoryName) {
        switch (categoryName) {
            case "小物":
                return "1";
            case "服装":
                return "2";
            case "户外运动":
                return "3";
            case "电子产品":
                return "4";
            case "家居用品":
                return "5";
            case "玩具":
                return "6";
            case "大件":
                return "7";
            default:
                return "8";
        }
    }

    private String switchOrderCategoryFromCategoryName(String categoryName) {
        switch (categoryName) {
            case "单票单清":
                return "2";
            case "虚拟海外仓":
                return "3";
            case "海外仓代发订单":
            default:
                return "1";
        }
    }

    @GetMapping("/warehousing/excel/{status}")
    @SuppressWarnings("rawtypes")
    public void getWarehousingExcel(
            @PathVariable String status,
            HttpServletResponse httpServletResponse,
            @RequestParam String method,
            @RequestParam(required = false) String creator)
            throws IOException {
        String fileName = "入库单信息.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader(
                "Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        List<Warehousing> warehousingList =
                warehousingMapper.listByOwnerAndStatus(new Page(1, 500), creator, status, method);
        warehousingList.forEach(
                warehousing -> {
                    warehousing.setStatusDesc(labelCache.getLabel("warehousing_" + warehousing.getStatus()));
                    List<WarehousingContent> warehousingContentList = warehousing.getWarehousingContentList();
                    if (warehousingContentList != null && !warehousingContentList.isEmpty()) {
                        if (warehousingContentList.size() >= 1) {
                            WarehousingContent warehousingContent1 = warehousingContentList.get(0);
                            warehousing.setSku1(warehousingContent1.getSku());
                            warehousing.setName1(warehousingContent1.getName());
                            warehousing.setNum1(warehousingContent1.getTotalNum());
                            warehousing.setWrapType1(warehousingContent1.getWrapType());
                        }
                        if (warehousingContentList.size() >= 2) {
                            WarehousingContent warehousingContent2 = warehousingContentList.get(1);
                            warehousing.setSku2(warehousingContent2.getSku());
                            warehousing.setName2(warehousingContent2.getName());
                            warehousing.setNum2(warehousingContent2.getTotalNum());
                            warehousing.setWrapType2(warehousingContent2.getWrapType());
                        }
                        if (warehousingContentList.size() >= 3) {
                            WarehousingContent warehousingContent3 = warehousingContentList.get(2);
                            warehousing.setSku3(warehousingContent3.getSku());
                            warehousing.setName3(warehousingContent3.getName());
                            warehousing.setNum3(warehousingContent3.getTotalNum());
                            warehousing.setWrapType3(warehousingContent3.getWrapType());
                        }
                    }
                });
        writeServletResp(httpServletResponse, warehousingList, Warehousing.class);
    }

    @PostMapping("/file/list")
    public Json getFileListByUuid(@RequestBody Map<String, String> orderNoMap) {
        ManualOrder manualOrder = orderMapper.getOrdDetail(orderNoMap.get("orderNo"));
        String files = manualOrder.getFiles();
        if (StringUtils.isNotBlank(files)) {
            List<File> fileList = fileMapper.getFileListByUuid(Arrays.asList(files.split(";")));
            return Json.succ().data(fileList);
        } else {
            return Json.succ().data(null);
        }
    }
}
