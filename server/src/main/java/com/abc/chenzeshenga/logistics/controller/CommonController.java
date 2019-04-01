package com.abc.chenzeshenga.logistics.controller;

import com.abc.chenzeshenga.logistics.cache.JapanAddressCache;
import com.abc.chenzeshenga.logistics.cache.LabelCache;
import com.abc.chenzeshenga.logistics.mapper.FileMapper;
import com.abc.chenzeshenga.logistics.mapper.ImgMapper;
import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.UserFileRecordMapper;
import com.abc.chenzeshenga.logistics.model.*;
import com.abc.chenzeshenga.logistics.util.CommonUtil;
import com.abc.chenzeshenga.logistics.util.SkuUtil;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.vo.Json;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author chenzesheng
 * @version 1.0
 */
@RestController @Slf4j public class CommonController {

    private static final String CARRIER = "carrier_";

    @Resource private ImgMapper imgMapper;

    @Resource private OrderMapper orderMapper;

    @Resource private FileMapper fileMapper;

    @Resource private UserFileRecordMapper userFileRecordMapper;

    private JapanAddressCache japanAddressCache;

    private LabelCache labelCache;

    public CommonController(JapanAddressCache japanAddressCache, LabelCache labelCache) {
        this.japanAddressCache = japanAddressCache;
        this.labelCache = labelCache;
    }

    @GetMapping("/generate/pk") public Json getOrderNo() {
        String pk = CommonUtil.generate();
        return Json.succ().data(pk);
    }

    @GetMapping("/generate/sku") public Json getDySku() {
        String sku = SkuUtil.generateDySku();
        return Json.succ().data(sku);
    }

    @RequestMapping(value = "/img/{uuid}", produces = MediaType.IMAGE_JPEG_VALUE) @ResponseBody public byte[] getImg(@PathVariable String uuid) {
        Img img = imgMapper.selectByPrimaryKey(uuid);
        return img.getImg();
    }

    @PostMapping @RequestMapping(value = "/img") public Json putImg(@RequestParam(value = "file") MultipartFile multipartFile, String uuid)
        throws IOException {
        Img img = new Img(uuid, multipartFile.getBytes());
        imgMapper.insert(img);
        return Json.succ();
    }

    @GetMapping("/ord/excel/{status}") public void getOrdExcel(HttpServletResponse httpServletResponse, @PathVariable String status)
        throws IOException {
        String fileName = "订单状态.xlsx";
        httpServletResponse.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "utf-8"));
        Map<String, String> request = new HashMap<>(2);
        request.put("cname", UserUtils.getUserName());
        request.put("status", status);
        List<ManualOrder> manualOrderList = orderMapper.listAllByUsername(request);
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
            }
            JpDetailAddress to = japanAddressCache
                .getJpDetailAddress(Integer.valueOf(manualOrder.getToKenId()), Integer.valueOf(manualOrder.getToCityId()),
                    Integer.valueOf(manualOrder.getToTownId()));
            manualOrder.setToKenName(to.getKenName());
            manualOrder.setToCityName(to.getCityName());
            manualOrder.setToTownName(to.getTownName());
            manualOrder.setCategoryName(labelCache.getLabel("category_" + manualOrder.getCategory()));
            manualOrder.setStatusDesc(labelCache.getLabel("ord_status_" + manualOrder.getStatus()));
            manualOrder.setCarrierName(labelCache.getLabel(CARRIER + manualOrder.getCarrierNo()));
        });
        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();
        ExcelWriter excelWriter = new ExcelWriter(servletOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, ManualOrder.class);
        excelWriter.write(manualOrderList, sheet1);
        excelWriter.finish();
        httpServletResponse.flushBuffer();
    }

    @PostMapping("/ord/excel") public Json parseExcel(@RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        File file = new File(uuid, multipartFile.getBytes());
        fileMapper.insert(file);
        String username = UserUtils.getUserName();
        UserFileRecord userFileRecord = new UserFileRecord(multipartFile.getOriginalFilename(), new Date());
        userFileRecord.setUid(username);
        userFileRecord.setFileUuid(uuid);
        userFileRecordMapper.insert(userFileRecord);
        InputStream inputStream = multipartFile.getInputStream();
        List<Object> manualOrderList = EasyExcelFactory.read(inputStream, new Sheet(1, 0, ManualOrder.class));
        manualOrderList.forEach(manualOrder -> {
            log.info(manualOrder.toString());
        });
        return Json.succ();
    }

    @GetMapping("/ord/csv/{ordno}") public void getOrdCsv(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/excel/{ordno}") public void getWarehousingExcel(@PathVariable String ordno) {

    }

    @GetMapping("/warehousing/csv/{ordno}") public void getWarehousingCsv(@PathVariable String ordno) {

    }

}
