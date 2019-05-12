package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.cache.OrderCache;
import com.abc.chenzeshenga.logistics.mapper.*;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.util.CommonUtil;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * @author chenzesheng
 * @version 1.0
 */
@RestController @Slf4j public class CommonController {

    class Constant {
        private static final String CARRIER = "carrier_";
    }

    private static final String CARRIER = "carrier_";

    @Resource private ImgMapper imgMapper;

    @Resource private OrderMapper orderMapper;

    @Resource private FileMapper fileMapper;

    @Resource private UserFileRecordMapper userFileRecordMapper;

    @Resource private ProductMapper productMapper;

    @Resource private TemplateMapper templateMapper;

    private JapanAddressCache japanAddressCache;

    private LabelCache labelCache;

    private OrderCache orderCache;

    private ExecutorService pool;

    @Autowired
    public CommonController(JapanAddressCache japanAddressCache, LabelCache labelCache, OrderCache orderCache,
        ExecutorService pool) {
        this.japanAddressCache = japanAddressCache;
        this.labelCache = labelCache;
        this.orderCache = orderCache;
        this.pool = pool;
    }

    @GetMapping("/generate/pk") public Json getOrderNo() {
        String pk =
            CommonUtil.generate() + "-" + (Integer.valueOf(orderMapper.getOrderSeq().getOrderNo().split("-")[1]) + 1);
        return Json.succ().data(pk);
    }

    @GetMapping("/generate/sku") public Json getDySku() {
        String sku = SkuUtil.generateDySku();
        return Json.succ().data(sku);
    }

    @RequestMapping(value = "/img/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE) @ResponseBody
    public byte[] getImg(@PathVariable String uuid) {
        Img img = imgMapper.selectByPrimaryKey(uuid);
        return img.getImg();
    }

    @GetMapping(value = "/template/file/{uuid}") @ResponseBody
    public void file(@PathVariable String uuid, HttpServletResponse httpServletResponse) throws IOException {
        File file = fileMapper.selectByPrimaryKey(uuid);
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse
            .setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "utf-8"));
        httpServletResponse.getOutputStream().write(file.getFile());
        httpServletResponse.flushBuffer();
    }

    @PostMapping @RequestMapping(value = "/img")
    public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile, String uuid) throws IOException {
        Img img = new Img(uuid, multipartFile.getBytes());
        imgMapper.insert(img);
        return Json.succ();
    }

    @GetMapping("/ord/excel/{status}")
    public void getOrdExcel(HttpServletResponse httpServletResponse, @PathVariable String status) throws IOException {
        String fileName = "订单状态.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse
            .setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        Map<String, String> request = new HashMap<>(2);
        request.put("cname", UserUtils.getUserName());
        request.put("status", status);
        List<ManualOrder> manualOrderList = orderMapper.listAllByUsername(request);
        manualOrderList.forEach(manualOrder -> {
            log.info(manualOrder.toString());
            if (StringUtils.isEmpty(manualOrder.getFromKenId())) {
                manualOrder.setFromAddressDesc(manualOrder.getFromDetailAddress());
            } else {
                JpDetailAddress from = japanAddressCache.getJpDetailAddress(Integer.valueOf(manualOrder.getFromKenId()),
                    Integer.valueOf(manualOrder.getFromCityId()), Integer.valueOf(manualOrder.getFromTownId()));
                manualOrder.setFromKenName(from.getKenName());
                manualOrder.setFromCityName(from.getCityName());
                manualOrder.setFromTownName(from.getTownName());
            }
            JpDetailAddress to = japanAddressCache.getJpDetailAddress(Integer.valueOf(manualOrder.getToKenId()),
                Integer.valueOf(manualOrder.getToCityId()), Integer.valueOf(manualOrder.getToTownId()));
            manualOrder.setToKenName(to.getKenName());
            manualOrder.setToCityName(to.getCityName());
            manualOrder.setToTownName(to.getTownName());
            manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
            manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
            manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
            manualOrder.setCollectDesc("false".equals(manualOrder.getCollect()) ? "否" : "是");
            List<ManualOrderContent> manualOrderContents = manualOrder.getManualOrderContents();
            try {
                ManualOrderContent manualOrderContent1 = manualOrderContents.get(0);
                if (ObjectUtils.anyNotNull(manualOrderContent1)) {
                    manualOrder.setSku1(manualOrderContent1.getSku());
                    manualOrder.setName1(manualOrderContent1.getName());
                    manualOrder.setNum1(manualOrderContent1.getNum());
                }
            } catch (IndexOutOfBoundsException e) {
                log.error("error stack info ", e);
            }
            try {
                ManualOrderContent manualOrderContent2 = manualOrderContents.get(1);
                if (ObjectUtils.anyNotNull(manualOrderContent2)) {
                    manualOrder.setSku2(manualOrderContent2.getSku());
                    manualOrder.setName2(manualOrderContent2.getName());
                    manualOrder.setNum2(manualOrderContent2.getNum());
                }
            } catch (IndexOutOfBoundsException e) {
                log.error("error stack info ", e);
            }
            try {
                ManualOrderContent manualOrderContent3 = manualOrderContents.get(2);
                if (ObjectUtils.anyNotNull(manualOrderContent3)) {
                    manualOrder.setSku3(manualOrderContent3.getSku());
                    manualOrder.setName3(manualOrderContent3.getName());
                    manualOrder.setNum3(manualOrderContent3.getNum());
                }
            } catch (IndexOutOfBoundsException e) {
                log.error("error stack info ", e);
            }
        });
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, ManualOrder.class);
        excelWriter.write(manualOrderList, sheet1);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    @GetMapping("/product/excel/{status}")
    public void getProductExcel(HttpServletResponse httpServletResponse, @PathVariable String status)
        throws IOException {
        String fileName = "商品信息.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse
            .setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        List<Product> productList =
            productMapper.listByStatusWithUser(new Page(1, 500), UserUtils.getUserName(), status);
        productList.forEach(product -> {
            if (StringUtils.isEmpty(product.getCategoryName())) {
                product.setCategoryName(labelCache.getLabel("classification_" + product.getCategory()));
            }
            if ("0".equals(product.getStatus())) {
                product.setStatusDesc("审核中");
            } else if ("1".equals(product.getStatus())) {
                product.setStatusDesc("在库");
            }
        });
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, Product.class);
        excelWriter.write(productList, sheet1);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    @PostMapping("/ord/excel") public Json parseExcel(@RequestParam(value = "file") MultipartFile multipartFile)
        throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes());
        fileMapper.insert(file);
        String username = UserUtils.getUserName();
        UserFileRecord userFileRecord = new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
        userFileRecord.setUid(username);
        userFileRecord.setFileUuid(uuid);
        userFileRecordMapper.insert(userFileRecord);
        pool.execute(() -> {
            try {
                InputStream inputStream = multipartFile.getInputStream();
                List<Object> manualOrderList =
                    EasyExcelFactory.read(inputStream, new Sheet(1, 0, ManualOrder4Input.class));
                manualOrderList.forEach(manualOrder -> {
                    if (manualOrderList.indexOf(manualOrder) >= 1) {
                        ManualOrder4Input manualOrder4Input;
                        if (manualOrder instanceof ManualOrder4Input) {
                            manualOrder4Input = (ManualOrder4Input)manualOrder;
                            log.info(manualOrder4Input.toString());

                        }
                    }
                });
            } catch (IOException e) {
                log.error("error stack info ", e);
            }
        });
        return Json.succ();
    }

    @PostMapping("/product/excel")
    public Json parseProductExcel(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes());
        fileMapper.insert(file);
        String username = UserUtils.getUserName();
        UserFileRecord userFileRecord = new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
        userFileRecord.setUid(username);
        userFileRecord.setFileUuid(uuid);
        userFileRecordMapper.insert(userFileRecord);
        InputStream inputStream = multipartFile.getInputStream();
        List<Object> productList = EasyExcelFactory.read(inputStream, new Sheet(1, 1, Product.class));
        for (Object o : productList) {
            Product product = (Product)o;
            product.setCategory(switchFromCategoryName(product.getCategoryName()));
            product.setDySku(SkuUtil.generateDySku());
            product.setStatus("0");
            product.setCreatedBy(username);
            product.setUpdateBy(username);
            Date curr = new Date();
            product.setUpdateOn(curr);
            product.setCreateOn(curr);
            productMapper.insertSelective(product);
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

    @GetMapping("/ord/csv/{ordno}") public void getOrdCsv(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/excel/{ordno}") public void getWarehousingExcel(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/csv/{ordno}") public void getWarehousingCsv(@PathVariable String ordno) {

    }

}
