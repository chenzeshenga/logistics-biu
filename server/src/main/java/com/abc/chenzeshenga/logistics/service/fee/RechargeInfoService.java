package com.abc.chenzeshenga.logistics.service.fee;

import com.abc.chenzeshenga.logistics.mapper.fee.RechargeInfoMapper;
import com.abc.chenzeshenga.logistics.model.common.PageData;
import com.abc.chenzeshenga.logistics.model.common.SqlLimit;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfo;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoHead;
import com.abc.chenzeshenga.logistics.model.fee.RechargeInfoReq;
import com.abc.chenzeshenga.logistics.service.common.FileService;
import com.abc.chenzeshenga.logistics.util.DateUtil;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.SqlUtils;
import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.Sheet;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author chenzeshenga
 */
@Service
@Slf4j
public class RechargeInfoService extends ServiceImpl<RechargeInfoMapper, RechargeInfo> {

    private final FileService fileService;

    @Autowired
    public RechargeInfoService(FileService fileService) {
        this.fileService = fileService;
    }

    public PageData<RechargeInfo> selectRechargeInfo(RechargeInfoReq rechargeInfoReq) {
        SqlLimit sqlLimit = SqlUtils.generateSqlLimit(rechargeInfoReq);
        List<RechargeInfo> rechargeInfoList = baseMapper.selectRechargeInfo(sqlLimit, rechargeInfoReq.getUserId());
        long total = baseMapper.countRechargeInfoList(rechargeInfoReq.getUserId());
        rechargeInfoReq.setTotal(total);
        PageData<RechargeInfo> rechargeInfoPageData = new PageData<>();
        rechargeInfoPageData.setData(rechargeInfoList);
        rechargeInfoPageData.setPagination(rechargeInfoReq);
        return rechargeInfoPageData;
    }

    @Override
    public boolean insert(RechargeInfo entity) {
        entity.setUuid(SnowflakeIdWorker.generateStrId());
        return super.insert(entity);
    }


    public void batchImport(MultipartFile multipartFile) throws IOException {
        fileService.upload(multipartFile);
        List<Object> rechargeInfoObjList = EasyExcelFactory.read(multipartFile.getInputStream(), new Sheet(1, 1, RechargeInfoHead.class));
        for (Object rechargeInfoObj : rechargeInfoObjList) {
            if (rechargeInfoObj instanceof RechargeInfoHead) {
                RechargeInfoHead rechargeInfoHead = (RechargeInfoHead) rechargeInfoObj;
                try {
                    RechargeInfo rechargeInfo = new RechargeInfo();
                    rechargeInfo.setUserId(rechargeInfoHead.getUserId());
                    if (StringUtils.isNotEmpty(rechargeInfoHead.getRechargeDate())) {
                        rechargeInfo.setRechargeDate(DateUtil.getCustomDateFromDateStr(rechargeInfoHead.getRechargeDate(),"yyyyMMdd"));
                    }
                    if (StringUtils.isNotEmpty(rechargeInfoHead.getAmountInJpy())) {
                        rechargeInfo.setAmountInJpy(new BigDecimal(rechargeInfoHead.getAmountInJpy()));
                    }
                    if (StringUtils.isNotEmpty(rechargeInfoHead.getAmountInCny())) {
                        rechargeInfo.setAmountInCny(new BigDecimal(rechargeInfoHead.getAmountInCny()));
                    }
                    if (StringUtils.isNotEmpty(rechargeInfoHead.getExchangeRate())) {
                        rechargeInfo.setExchangeRate(new BigDecimal(rechargeInfoHead.getExchangeRate()));
                    }
                    rechargeInfo.setPayMethod(rechargeInfoHead.getPayMethod());
                    rechargeInfo.setUuid(SnowflakeIdWorker.generateStrId());
                    rechargeInfo.setCreatedDate(new Date());
                    insert(rechargeInfo);
                } catch (Exception e) {
                    log.error("data {} import fail ", rechargeInfoHead, e);
                }
            }
        }
    }

}
