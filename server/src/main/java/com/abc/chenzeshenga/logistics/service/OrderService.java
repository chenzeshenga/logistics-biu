package com.abc.chenzeshenga.logistics.service;

import com.abc.chenzeshenga.logistics.cache.ChannelCache;
import com.abc.chenzeshenga.logistics.mapper.OrderMapper;
import com.abc.chenzeshenga.logistics.mapper.file.FileRecordMapper;
import com.abc.chenzeshenga.logistics.model.ManualOrder;
import com.abc.chenzeshenga.logistics.model.ManualOrderContent;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoHead;
import com.abc.chenzeshenga.logistics.model.file.FileRecord;
import com.abc.chenzeshenga.logistics.model.ord.ManualOrderHead;
import com.abc.chenzeshenga.logistics.model.ord.ManualOrderHeadReq;
import com.abc.chenzeshenga.logistics.model.ord.OrderPackage;
import com.abc.chenzeshenga.logistics.service.common.FileService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.FileUtils;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author chenzeshenga
 * @version 1.0
 */
@Slf4j
@Service
public class OrderService extends ServiceImpl<OrderMapper, ManualOrder> {

    private static final String tempPath = "/app/temp";

    private final ChannelCache channelCache;

    private final FileRecordMapper fileRecordMapper;

    private final FileService fileService;

    @Autowired
    public OrderService(ChannelCache channelCache, FileRecordMapper fileRecordMapper, FileService fileService) {
        this.channelCache = channelCache;
        this.fileRecordMapper = fileRecordMapper;
        this.fileService = fileService;
    }

    public Page<ManualOrder> list(Page page, String cname, String type, String status) {
        return page.setRecords(baseMapper.list(page, cname, type, status));
    }

    public Page<ManualOrder> listV2(Page page, String cname, String type, String status) {
        long from = (page.getCurrent() - 1) * (long) page.getSize();
        long to = page.getCurrent() * (long) page.getSize();
        page.setRecords(baseMapper.listV2(cname, type, status, from, to));
        page.setTotal(baseMapper.countList(cname, type, status));
        return page;
    }

    public Page<ManualOrder> listByRange(
        Page page,
        String type,
        String status,
        Date from,
        Date to,
        String ordno,
        String creator,
        String channelCode,
        String trackNo,
        String userCustomOrderNo,
        int pickup) {
        Page<ManualOrder> manualOrderPage = new Page<>();
        long fromIdx = (page.getCurrent() - 1) * (long) page.getSize();
        long toIdx = page.getCurrent() * (long) page.getSize();
        List<ManualOrder> manualOrderList =
            baseMapper.listByRange(
                type,
                status,
                from,
                to,
                ordno,
                creator,
                channelCode,
                trackNo,
                userCustomOrderNo,
                pickup,
                fromIdx,
                toIdx);
        for (ManualOrder manualOrder : manualOrderList) {
            List<ManualOrderContent> manualOrderContentList =
                baseMapper.listContent2(manualOrder.getOrderNo());
            List<OrderPackage> orderPackageList = baseMapper.listPackage(manualOrder.getOrderNo());
            manualOrder.setManualOrderContents(manualOrderContentList);
            manualOrder.setOrderPackageList(orderPackageList);
            manualOrder.setChannelDesc(channelCache.channelLabel(manualOrder.getChannel()));
        }
        manualOrderPage.setRecords(manualOrderList);
        manualOrderPage.setSize(page.getSize());
        manualOrderPage.setTotal(
            baseMapper.countByRange(
                type,
                status,
                from,
                to,
                ordno,
                creator,
                channelCode,
                trackNo,
                userCustomOrderNo,
                pickup));
        manualOrderPage.setCurrent(page.getCurrent());
        return manualOrderPage;
    }

    public void pickup() {
    }

    public FileRecord generateTemplate() throws FileNotFoundException {
        String uuid = SnowflakeIdWorker.generateStrId();
        String currMonth = DateUtil.getMonthFromDate(new Date());
        FileUtils.createDirectory(tempPath + File.separator + currMonth);
        File tmpFile = new File(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        FileOutputStream fileOutputStream = new FileOutputStream(tmpFile);
        ExcelWriter excelWriter = new ExcelWriter(fileOutputStream, ExcelTypeEnum.XLSX);
        Sheet sheet1 = new Sheet(1, 0, ManualOrderHead.class);
        excelWriter.write(null, sheet1);
        excelWriter.finish();
        FileRecord fileRecord = new FileRecord();
        fileRecord.setUuid(uuid);
        fileRecord.setFileName(uuid + ".xlsx");
        fileRecord.setAbsolutePath(tempPath + File.separator + currMonth + File.separator + uuid + ".xlsx");
        fileRecord.setUploadUser("sys");
        fileRecord.setUploadDate(new Date());
        fileRecordMapper.insert(fileRecord);
        return fileRecord;
    }

    public void batchImport(MultipartFile multipartFile) throws IOException {
        fileService.upload(multipartFile);
        List<Object> manualOrderHeadList = EasyExcelFactory.read(multipartFile.getInputStream(), new Sheet(1, 1, ManualOrderHeadReq.class));
        for (Object manualOrdHeadObj : manualOrderHeadList) {
            if (manualOrdHeadObj instanceof ManualOrderHeadReq) {
                ManualOrderHeadReq manualOrderHead = (ManualOrderHeadReq) manualOrdHeadObj;
                try {
                    ManualOrder manualOrder = new ManualOrder();
                    manualOrder.setOrderNo(manualOrderHead.getOrderNo());
                    manualOrder.setOrdUserFee(manualOrderHead.getOrdUserFee());
                    baseMapper.updateOrdFee(manualOrder);
                } catch (Exception e) {
                    log.error("data {} import fail ", manualOrderHead, e);
                }
            }
        }
    }

    public void feeConfirm(String orderNo) {
        ManualOrder manualOrder = baseMapper.selectById(orderNo);
        manualOrder.setConfirmed(true);
        manualOrder.setConfirmUser(UserUtils.getUserName());
        baseMapper.update(manualOrder);
    }

}
